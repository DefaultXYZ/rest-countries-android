package defaultxyz.countries.android.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import defaultxyz.countries.android.db.model.CurrencyEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(vararg currencies: CurrencyEntity): Completable

    @Query("SELECT * FROM currency")
    fun getAll(): Single<List<CurrencyEntity>>

    @Query("DELETE FROM currency")
    fun removeAll(): Completable
}