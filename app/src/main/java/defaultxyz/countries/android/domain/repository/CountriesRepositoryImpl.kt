package defaultxyz.countries.android.domain.repository

import defaultxyz.countries.android.db.dao.CountryDao
import defaultxyz.countries.android.db.dao.CurrencyDao
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.android.network.ApiClient
import io.reactivex.Single
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val countryDao: CountryDao,
    private val currencyDao: CurrencyDao
) : CountriesRepository {
    override fun fetchCountryList(): Single<List<Country>> = Single.just(emptyList())

    companion object {
        private const val expireTimeInDays = 1
    }
}