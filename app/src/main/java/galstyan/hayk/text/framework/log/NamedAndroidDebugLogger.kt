package galstyan.hayk.text.framework.log

import android.util.Log
import galstyan.hayk.text.Logger
import javax.inject.Inject

class NamedAndroidDebugLogger @Inject constructor(private val name: String) : Logger {
    override fun log(tag: String, message: String?) {
        Log.d(name, "$tag: $message")
    }
}