package defaultxyz.countries.core.logging

import android.util.Log

fun Any.info(throwable: Throwable? = null, message: () -> String = { "" }) {
    val msg = message().getNotBlank() ?: throwable?.localizedMessage.orEmpty()
    Log.i(this::class.java.simpleName, msg, throwable)
}

fun Any.debug(throwable: Throwable? = null, message: () -> String = { "" }) {
    val msg = message().getNotBlank() ?: throwable?.localizedMessage.orEmpty()
    Log.d(this::class.java.simpleName, msg, throwable)
}

fun Any.verbose(throwable: Throwable? = null, message: () -> String = { "" }) {
    val msg = message().getNotBlank() ?: throwable?.localizedMessage.orEmpty()
    Log.v(this::class.java.simpleName, msg, throwable)
}

fun Any.warning(throwable: Throwable? = null, message: () -> String = { "" }) {
    val msg = message().getNotBlank() ?: throwable?.localizedMessage.orEmpty()
    Log.w(this::class.java.simpleName, msg, throwable)
}

fun Any.error(throwable: Throwable? = null, message: () -> String = { "" }) {
    val msg = message().getNotBlank() ?: throwable?.localizedMessage.orEmpty()
    Log.e(this::class.java.simpleName, msg, throwable)
}

private fun String.getNotBlank(): String? = if (isNotBlank()) this else null
