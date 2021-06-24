package galstyan.hayk.text.ui.document

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.R
import galstyan.hayk.text.databinding.FragmentDocumentBinding
import galstyan.hayk.text.ui.ViewBindingFragment
import galstyan.hayk.text.ui.pop


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
            setNavigationOnClickListener { pop() }
            setOnMenuItemClickListener { onOptionsItemSelected(it) }
        }

        viewModel.initialDocument?.let {
            binding.title.setText(it.title)
            binding.text.setText(it.text)
        }

        binding.title.doAfterTextChanged { viewModel.title = it }
        binding.text.doAfterTextChanged { viewModel.text = it }
    }


    override fun onPause() {
        super.onPause()
        viewModel.saveDocument()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteDocumentMenuAction -> deleteDocument()
            R.id.shareDocumentMenuAction -> shareDocument()
        }
        return true
    }


    private fun deleteDocument() {
        viewModel.removeDocument()
        pop()
    }


    private fun shareDocument() = startActivity(
        Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, viewModel.text)
                type = "text/plain"
            }, null
        )
    )


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