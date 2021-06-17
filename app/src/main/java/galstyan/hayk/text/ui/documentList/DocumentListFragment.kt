package galstyan.hayk.text.ui.documentList

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.text.R

@AndroidEntryPoint
class DocumentListFragment : Fragment(R.layout.fragment_document_list) {

    private val viewModel: DocumentListViewModel by viewModels()


}