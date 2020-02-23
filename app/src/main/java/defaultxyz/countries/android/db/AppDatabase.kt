package defaultxyz.countries.android.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import defaultxyz.countries.android.db.dao.CountryDao
import defaultxyz.countries.android.db.dao.CurrencyDao
import defaultxyz.countries.android.db.model.CountryEntity
import defaultxyz.countries.android.db.model.CurrencyEntity
import defaultxyz.countries.android.db.type.converters.CalendarConverter
import defaultxyz.countries.android.db.type.converters.StringListConverter

@Database(
    entities = [
        CountryEntity::class,
        CurrencyEntity::class
    ],
    version = 1
)
@TypeConverters(
    StringListConverter::class,
    CalendarConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val DB_NAME = "db_countries"
    }
}