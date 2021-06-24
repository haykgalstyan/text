package galstyan.hayk.text.ui.util

import android.content.Context
import android.text.Editable
import androidx.fragment.app.Fragment

fun Context.toPx(dp: Int): Int = dp * this.resources.displayMetrics.density.toInt()
fun Context.toDp(px: Int): Int = px / this.resources.displayMetrics.density.toInt()

fun Fragment.toPx(dp: Int): Int = this.requireContext().toPx(dp)
fun Fragment.toDp(px: Int): Int = this.requireContext().toDp(px)

fun String?.toEditable(): Editable? {
    if (this == null) return null
    return Editable.Factory.getInstance().newEditable(this)
}
