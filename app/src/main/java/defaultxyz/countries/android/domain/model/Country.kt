package defaultxyz.countries.android.domain.model

data class Country(
    val name: String,
    val domainList: List<String>,
    val phoneCodeList: List<String>,
    val currency: Currency
)