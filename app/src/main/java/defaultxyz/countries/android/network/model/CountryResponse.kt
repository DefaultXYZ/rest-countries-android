package defaultxyz.countries.android.network.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val name: String,
    @SerializedName("topLevelDomain") val domainList: List<String>,
    @SerializedName("callingCodes") val phoneCodes: List<String>,
    @SerializedName("currencies") val currencyList: List<CurrencyResponse>
)