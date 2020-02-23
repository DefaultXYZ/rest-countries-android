package defaultxyz.countries.android.db.converters

import defaultxyz.countries.android.db.model.CountryCurrencyEntity
import defaultxyz.countries.android.db.model.CurrencyEntity
import defaultxyz.countries.android.domain.model.Country
import defaultxyz.countries.android.domain.model.Currency

fun CountryCurrencyEntity.toModel(): Country =
    Country(
        name = country.name,
        domainList = country.domainList,
        phoneCodeList = country.phoneCodes,
        currencyList = currencyList.map(CurrencyEntity::toModel)
    )

fun List<CountryCurrencyEntity>.toModelList(): List<Country> =
    map(CountryCurrencyEntity::toModel)

fun CurrencyEntity.toModel(): Currency =
    Currency(
        name = name,
        code = code,
        symbol = symbol
    )