package defaultxyz.countries.android.db.converters

import defaultxyz.countries.android.db.model.CountryEntity
import defaultxyz.countries.android.db.model.CurrencyEntity
import defaultxyz.countries.android.network.model.CountryResponse
import defaultxyz.countries.android.network.model.CurrencyResponse

fun CountryResponse.toEntity(): CountryEntity =
    CountryEntity(
        name = name,
        domainList = domainList,
        phoneCodes = phoneCodes
    )

fun List<CountryResponse>.toEntityList(): List<CountryEntity> =
    map(CountryResponse::toEntity)

fun CurrencyResponse.toEntity(): CurrencyEntity =
    CurrencyEntity(
        name = name,
        code = code,
        symbol = symbol
    )