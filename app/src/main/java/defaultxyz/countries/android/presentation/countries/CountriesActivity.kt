package defaultxyz.countries.android.presentation.countries

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import defaultxyz.countries.android.R
import defaultxyz.countries.core.base.BaseActivity
import defaultxyz.countries.core.extensions.lazyViewModel
import kotlinx.android.synthetic.main.activity_countries.*
import javax.inject.Inject

class CountriesActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: CountriesViewModel by lazyViewModel { factory }

    private val adapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        countryList.adapter = adapter
        (countryList.layoutManager as? LinearLayoutManager)?.orientation?.let { orientation ->
            countryList.addItemDecoration(DividerItemDecoration(baseContext, orientation))
        }

        viewModel.countries.observe {
            adapter.updateItems(it)

            swipe.isRefreshing = false
        }

        swipe.setOnRefreshListener {
            viewModel.updateCountryList()
        }

        swipe.post {
            viewModel.updateCountryList()
            swipe.isRefreshing = true
        }
    }
}
