package defaultxyz.countries.android.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import defaultxyz.countries.android.db.AppDatabase
import defaultxyz.countries.android.db.converters.toEntityList
import defaultxyz.countries.android.db.dao.CountryDao
import defaultxyz.countries.android.db.dao.CurrencyDao
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.android.domain.model.Currency
import defaultxyz.countries.android.domain.repository.CountriesRepository
import defaultxyz.countries.android.domain.repository.CountriesRepositoryImpl
import defaultxyz.countries.android.network.ApiClient
import defaultxyz.countries.android.network.model.CountryResponse
import defaultxyz.countries.android.network.model.CurrencyResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    private lateinit var database: AppDatabase

    private lateinit var countryDao: CountryDao
    private lateinit var currencyDao: CurrencyDao
    private lateinit var apiClient: ApiClient

    private lateinit var repository: CountriesRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        countryDao = database.countryDao()
        currencyDao = database.currencyDao()

        apiClient = mock {
            on { getCountries() } doReturn Single.just(testResponse)
        }

        repository = CountriesRepositoryImpl(
            apiClient, countryDao, currencyDao
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `Should load data from API`() {
        repository.fetchCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertValue(testResponse.toModels())
    }

    @Test
    fun `Should load data from Database`() {
        repository.fetchCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertComplete()

        repository.fetchCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertComplete()

        verify(apiClient, times(1)).getCountries()
    }

    @Test
    fun `Should refresh expired data`() {
        val expiredTime = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -7)
        }
        countryDao.insertCountries(
            *testResponse.toEntityList()
                .map { it.copy(insertTime = expiredTime) }
                .toTypedArray()
        ).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertComplete()

        repository.fetchCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertComplete()

        verify(apiClient, times(1)).getCountries()
    }

    companion object {
        private val testResponse = listOf(
            CountryResponse(
                name = "a",
                domainList = listOf(".b"),
                phoneCodes = listOf("12"),
                currencyList = listOf(
                    CurrencyResponse(
                        "azs",
                        "123",
                        "qwe"
                    )
                )
            )
        )
    }
}

private fun CountryResponse.toModel(): Country =
    Country(
        name = name,
        domainList = domainList,
        phoneCodeList = phoneCodes,
        currencyList = currencyList.map {
            Currency(
                name = it.name,
                code = it.code,
                symbol = it.symbol
            )
        }
    )

private fun List<CountryResponse>.toModels(): List<Country> =
    map(CountryResponse::toModel)