package defaultxyz.countries.android.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import defaultxyz.countries.android.presentation.countries.CountriesActivity

@Module
interface ActivityBinder {

    @ContributesAndroidInjector
    fun contributesMainActivity(): CountriesActivity
}