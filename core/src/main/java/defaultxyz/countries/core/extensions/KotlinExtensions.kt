package defaultxyz.countries.core.extensions

fun <A, B> Pair<A?, B?>.toNotNullPair(): Pair<A, B>? =
    first?.let { a -> second?.let { b -> a to b } }