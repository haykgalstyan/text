package galstyan.hayk.text.ui.document

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.text.R

@AndroidEntryPoint
class DocumentFragment : Fragment(R.layout.fragment_document) {

    private val viewModel: DocumentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}