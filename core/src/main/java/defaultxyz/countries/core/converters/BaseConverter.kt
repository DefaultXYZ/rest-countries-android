package defaultxyz.countries.core.converters

abstract class BaseConverter<A : Any, B : Any> {
    abstract fun from(value: A): B?
    abstract fun to(value: B): A?

    fun fromList(aList: List<A>): List<B> = aList.mapNotNull(::from)

    fun toList(bList: List<B>): List<A> = bList.mapNotNull(::to)
}