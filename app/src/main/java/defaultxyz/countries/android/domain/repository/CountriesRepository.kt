package defaultxyz.countries.android.domain.repository

import defaultxyz.countries.android.domain.model.Country
import io.reactivex.Single

interface CountriesRepository {
    fun fetchCountryList(): Single<List<Country>>
}