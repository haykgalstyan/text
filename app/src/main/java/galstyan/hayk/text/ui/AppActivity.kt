package galstyan.hayk.text.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.text.Logger
import galstyan.hayk.text.R
import galstyan.hayk.text.ui.documentList.DocumentListFragment
import javax.inject.Inject


@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<DocumentListFragment>(R.id.fragment_container_view)
            }
        }
    }
}