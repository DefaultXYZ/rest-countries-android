package defaultxyz.countries.android.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context =
        application.applicationContext
}