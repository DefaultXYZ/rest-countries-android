package defaultxyz.countries.android.presentation.countries

import android.os.Bundle
import defaultxyz.countries.android.R
import defaultxyz.countries.core.base.BaseActivity

class CountriesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
    }
}
