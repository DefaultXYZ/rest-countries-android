package defaultxyz.countries.android.di

import dagger.Binds
import dagger.Module
import defaultxyz.countries.android.domain.repository.CountriesRepository
import defaultxyz.countries.android.domain.repository.CountriesRepositoryImpl

@Module
interface RepositoryBinder {

    @Binds
    fun bindsCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository
}