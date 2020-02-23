package defaultxyz.countries.android.db.dao

import androidx.room.*
import defaultxyz.countries.android.db.model.CountryCurrencyEntity
import defaultxyz.countries.android.db.model.CountryEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(vararg countries: CountryEntity): Completable

    @Query("SELECT * FROM country")
    fun getAll(): Single<List<CountryEntity>>

    @Transaction
    @Query("SELECT * FROM country")
    fun getCountryCurrencyList(): Single<List<CountryCurrencyEntity>>

    @Query("DELETE FROM country")
    fun removeAll(): Completable
}