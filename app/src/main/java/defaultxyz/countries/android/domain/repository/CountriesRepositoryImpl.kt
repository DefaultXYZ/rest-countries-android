package defaultxyz.countries.android.domain.repository

import defaultxyz.countries.android.domain.model.Country
import io.reactivex.Single
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor() : CountriesRepository {
    override fun fetchCountryList(): Single<List<Country>> = Single.just(emptyList())
}