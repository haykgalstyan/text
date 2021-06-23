package galstyan.hayk.text.ui.document

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.R
import galstyan.hayk.text.ui.ViewBindingFragment
import galstyan.hayk.text.databinding.FragmentDocumentBinding

@AndroidEntryPoint
class DocumentFragment : ViewBindingFragment<FragmentDocumentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDocumentBinding =
        FragmentDocumentBinding::inflate

    private val viewModel: DocumentViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setNavigationOnClickListener { requireActivity().onBackPressed() }
            setOnMenuItemClickListener { onOptionsItemSelected(it) }
        }
        getDocument()?.let {
            binding.title.setText(it.title)
            binding.text.setText(it.text)
        }
    }


    override fun onPause() {
        super.onPause()

        // fixme: you are saving deleted doc :D
        //  store this in vm and delete on remove.

        viewModel.saveDocument(
            (getDocument() ?: Document()).copy(
                title = binding.title.text.toString(),
                text = binding.text.text.toString(),
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        getDocument()?.let {
        menu.findItem(R.id.deleteDocumentMenuAction).isVisible = true
//        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
//        getDocument()?.let {
        menu.findItem(R.id.deleteDocumentMenuAction).isVisible = true
//        }
        super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteDocumentMenuAction -> deleteDocument()
        }
        return true
    }


    private fun deleteDocument() {
        getDocument()?.let { viewModel.removeDocument(it) }
    }


    private fun getDocument(): Document? = (arguments?.get(argKeyDocument) as Document?)


    companion object {
        const val argKeyDocument = "document"
        fun newInstance(document: Document?) =
            DocumentFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(argKeyDocument, document)
                }
            }
    }
}