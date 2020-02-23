package defaultxyz.countries.core.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    /**
     * Lazily loads ViewModel from provided ViewModel factory
     */
    protected inline fun <reified T : ViewModel> Fragment.lazyViewModel(
        noinline factory: () -> ViewModelProvider.Factory
    ): Lazy<T> = lazy { ViewModelProviders.of(this, factory())[T::class.java] }

    protected fun <T : Any> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { observer(it) })
    }

    inline fun <reified T : Activity> startActivity(
        finishCurrent: Boolean = false,
        bundle: Bundle? = null
    ) {
        Intent(context, T::class.java).apply {
            if (bundle != null) {
                putExtras(bundle)
            }
            startActivity(this)
            if (finishCurrent) {
                activity?.finish()
            }
        }
    }
}