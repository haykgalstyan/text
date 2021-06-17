import androidx.fragment.app.Fragment

fun Fragment.push(layoutId: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction().replace(layoutId, fragment)
}