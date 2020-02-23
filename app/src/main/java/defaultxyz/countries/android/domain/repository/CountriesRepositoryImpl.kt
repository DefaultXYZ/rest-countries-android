package defaultxyz.countries.android.domain.repository

import defaultxyz.countries.android.db.converters.toEntity
import defaultxyz.countries.android.db.converters.toModelList
import defaultxyz.countries.android.db.dao.CountryDao
import defaultxyz.countries.android.db.dao.CurrencyDao
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.android.network.ApiClient
import defaultxyz.countries.android.network.model.CountryResponse
import defaultxyz.countries.android.network.model.CurrencyResponse
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val countryDao: CountryDao,
    private val currencyDao: CurrencyDao
) : CountriesRepository {
    override fun fetchCountryList(): Single<List<Country>> =
        countryDao.getCountryCurrencyList()
            .flatMap { data ->
                val insertTime = data.firstOrNull()?.country?.insertTime
                val expiredDate = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_MONTH, -expireTimeInDays)
                }
                if (insertTime == null || insertTime.before(expiredDate)) {
                    apiClient.getCountries()
                        .flatMap { countryResponses ->
                            updateCountries(countryResponses).andThen(
                                countryDao.getCountryCurrencyList().map {
                                    it.toModelList()
                                }
                            )
                        }
                } else {
                    Single.just(data).map {
                        it.toModelList()
                    }
                }
            }

    private fun updateCountries(responses: List<CountryResponse>): Completable {
        val data = responses.map {
            it.toEntity() to it.currencyList.map(CurrencyResponse::toEntity)
        }.toMap()
        return countryDao.removeAll().andThen(
            countryDao.insertCountries(*data.keys.toTypedArray()).map { countryIds ->
                countryIds.zip(data.values.toList()).flatMap { (countryId, currencies) ->
                    currencies.map { countryId to it }
                }.map { (countryId, currency) ->
                    currency.copy(countryId = countryId)
                }
            }.flatMapCompletable {
                currencyDao.insertCurrencies(*it.toTypedArray())
            }
        )
    }

    companion object {
        private const val expireTimeInDays = 1
    }
}