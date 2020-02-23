package defaultxyz.countries.android.db.model

import androidx.room.*
import java.util.*

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    @ColumnInfo(name = "domain_list") val domainList: List<String>,
    @ColumnInfo(name = "phone_codes") val phoneCodes: List<String>,
    @ColumnInfo(name = "insert_time") val insertTime: Calendar = Calendar.getInstance()
)

data class CountryCurrencyEntity(
    @Embedded
    val country: CountryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "country_id"
    )
    val currencyList: List<CurrencyEntity>
)

