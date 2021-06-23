package galstyan.hayk.text.ui.document

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.ViewBindingFragment
import galstyan.hayk.text.databinding.FragmentDocumentBinding

@AndroidEntryPoint
class DocumentFragment : ViewBindingFragment<FragmentDocumentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDocumentBinding =
        FragmentDocumentBinding::inflate

    private val viewModel: DocumentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        getDocument().let {
            binding.title.setText(it.title)
            binding.text.setText(it.text)
        }
    }


    override fun onPause() {
        super.onPause()
        viewModel.saveDocument(
            getDocument().copy(
                title = binding.title.text.toString(),
                text = binding.text.text.toString(),
            )
        )
    }

    private fun getDocument(): Document =
        (arguments?.get(argKeyDocument) as Document?) ?: Document()


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