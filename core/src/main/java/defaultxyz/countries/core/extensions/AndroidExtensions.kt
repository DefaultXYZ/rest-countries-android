package defaultxyz.countries.core.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun <reified T : ViewModel> FragmentActivity.lazyViewModel(noinline factory: () -> ViewModelProvider.Factory): Lazy<T> =
    lazy { ViewModelProviders.of(this, factory())[T::class.java] }

fun <T> Activity.showToast(message: T) {
    when (message) {
        is String -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        is Int -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun <T> Fragment.showToast(message: T) {
    when (message) {
        is String -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        is Int -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

inline fun <reified T : Activity> Activity.startActivity(
    finishCurrent: Boolean = false,
    bundle: Bundle? = null
) {
    Intent(this, T::class.java).apply {
        if (bundle != null) {
            putExtras(bundle)
        }
        startActivity(this)
        if (finishCurrent) {
            finish()
        }
    }
}

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun RecyclerView.useWithDivider() {
    addItemDecoration(
        DividerItemDecoration(
            context,
            requireNotNull((layoutManager as? LinearLayoutManager)?.orientation) {
                "Divider must be used with ${LinearLayoutManager::class} only."
            })
    )
}

fun TextView.asUnderline(underlined: Boolean = true) {
    paintFlags = if (underlined) {
        paintFlags xor Paint.UNDERLINE_TEXT_FLAG
    } else {
        paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
    }
}