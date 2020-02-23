package defaultxyz.countries.android.presentation.countries

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import defaultxyz.countries.android.databinding.ListItemCountryBinding
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.core.extensions.layoutInflater

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ItemViewHolder>() {
    private var items: List<CountryItem> = emptyList()

    fun updateItems(countries: List<Country>) {
        items = countries.map { country ->
            CountryItem(
                name = country.name,
                domain = country.domainList.joinToString(", "),
                phoneCode = country.phoneCodeList.joinToString(", "),
                currency = country.currencyList.joinToString(", ") { it.name }
            )
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ListItemCountryBinding.inflate(
                parent.layoutInflater(),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ItemViewHolder(
        private val binding: ListItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

data class CountryItem(
    val name: String,
    val domain: String,
    val phoneCode: String,
    val currency: String
)