package galstyan.hayk.text

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

//    @Inject
//    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()

//        logger.log("Environment", BuildConfig.BUILD_TYPE)
    }
}