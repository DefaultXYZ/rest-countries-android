package defaultxyz.countries.android.network.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val name: String = "",
    @SerializedName("topLevelDomain") val domainList: List<String> = emptyList(),
    @SerializedName("callingCodes") val phoneCodes: List<String> = emptyList(),
    @SerializedName("currencies") val currencyList: List<CurrencyResponse> = emptyList()
)