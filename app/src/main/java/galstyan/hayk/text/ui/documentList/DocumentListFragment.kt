package galstyan.hayk.text.ui.documentList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.text.Logger
import galstyan.hayk.text.R
import javax.inject.Inject

@AndroidEntryPoint
class DocumentListFragment : Fragment(R.layout.fragment_document_list) {

    @Inject
    lateinit var logger: Logger

    private val viewModel: DocumentListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDocumentList()
        viewModel.documentListObservable.observe(viewLifecycleOwner, { documentList ->
            if (documentList.isNotEmpty())
                documentList.forEach { logger.log("doc", it.name) }
            else logger.log("doc", "none")
        })
    }
}