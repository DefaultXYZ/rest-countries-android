package defaultxyz.countries.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    /*
    Activity Callback support
     */

    val error: LiveData<Throwable> = MutableLiveData<Throwable>()

    /*
    Composite Disposable support
     */

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    private fun Disposable.addToDisposables() = compositeDisposable.add(this)

    /*
    LiveData support
     */

    protected fun <T> LiveData<T>.postValue(value: T?) {
        (this as? MutableLiveData<T>)?.postValue(value)
    }

    /*
    RxJava support
     */

    protected fun <T : Any> Observable<T>.subscribe(
        onError: (Throwable) -> Unit = {},
        onNext: (T) -> Unit
    ) {
        subscribe(onNext, {
            it.printStackTrace()
            error.postValue(it)
            onError(it)
        }).addToDisposables()
    }

    protected fun <T : Any> Flowable<T>.subscribe(
        onError: (Throwable) -> Unit = {},
        onNext: (T) -> Unit
    ) {
        subscribe(onNext, {
            it.printStackTrace()
            error.postValue(it)
            onError(it)
        }).addToDisposables()
    }

    protected fun <T : Any> Single<T>.subscribe(
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        subscribe(onSuccess, {
            it.printStackTrace()
            error.postValue(it)
            onError(it)
        }).addToDisposables()
    }

    protected fun <T : Any> Maybe<T>.subscribe(
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        subscribe(onSuccess, {
            it.printStackTrace()
            error.postValue(it)
            onError(it)
        }).addToDisposables()
    }

    protected fun Completable.subscribe(
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit
    ) {
        subscribe(onComplete, {
            it.printStackTrace()
            error.postValue(it)
            onError(it)
        }).addToDisposables()
    }

}