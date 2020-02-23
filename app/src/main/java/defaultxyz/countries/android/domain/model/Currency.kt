package defaultxyz.countries.android.domain.model

data class Currency(
    val name: String,
    val code: String? = null,
    val symbol: String? = null
)