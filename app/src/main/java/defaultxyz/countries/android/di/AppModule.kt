package defaultxyz.countries.android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import defaultxyz.countries.android.BuildConfig
import defaultxyz.countries.android.db.AppDatabase
import defaultxyz.countries.android.db.dao.CountryDao
import defaultxyz.countries.android.db.dao.CurrencyDao
import defaultxyz.countries.android.network.ApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    fun providesApiClient(okHttpClient: OkHttpClient): ApiClient =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesCountryDao(database: AppDatabase): CountryDao =
        database.countryDao()

    @Provides
    fun providesCurrencyDao(database: AppDatabase): CurrencyDao =
        database.currencyDao()
}