package defaultxyz.countries.android.presentation.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.android.domain.repository.CountriesRepository
import defaultxyz.countries.core.base.BaseViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    private val repository: CountriesRepository
) : BaseViewModel() {

    val countries: LiveData<List<Country>> = MutableLiveData()

    fun updateCountryList() {
        repository.fetchCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeOnValue {
                countries.postValue(it)
            }
    }
}