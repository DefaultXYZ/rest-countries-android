package defaultxyz.countries.android.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import defaultxyz.countries.android.MainApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBinder::class,
        FragmentBinder::class,
        ViewModelBinder::class,
        RepositoryBinder::class
    ]
)
interface AppComponent {
    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}