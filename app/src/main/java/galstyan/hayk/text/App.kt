package galstyan.hayk.text

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()

        logger.log("Environment", BuildConfig.BUILD_TYPE)
    }
}