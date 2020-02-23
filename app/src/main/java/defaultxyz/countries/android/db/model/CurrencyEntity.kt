package defaultxyz.countries.android.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val code: String? = null,
    val symbol: String? = null,
    @ColumnInfo(name = "country_id") val countryId: Long? = null
)