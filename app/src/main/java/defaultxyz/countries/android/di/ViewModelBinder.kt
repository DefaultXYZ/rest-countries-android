package defaultxyz.countries.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import defaultxyz.countries.android.di.annotation.ViewModelKey
import defaultxyz.countries.android.presentation.countries.CountriesViewModel
import defaultxyz.countries.android.utils.ViewModelFactory

@Module
interface ViewModelBinder {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    fun bindsMainViewModel(viewModel: CountriesViewModel): ViewModel
}