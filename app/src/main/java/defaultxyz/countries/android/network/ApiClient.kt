package defaultxyz.countries.android.network

import defaultxyz.countries.android.network.model.CountryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {

    @GET("all?fields=name;topLevelDomain;callingCodes;currencies")
    fun getCountries(): Single<List<CountryResponse>>
}