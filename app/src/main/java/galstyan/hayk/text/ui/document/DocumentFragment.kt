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
    companion object {
        const val argKeyDocument = "document"
        fun newInstance(document: Document?) =
            DocumentFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(argKeyDocument, document)
                }
            }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDocumentBinding =
        FragmentDocumentBinding::inflate

    private val viewModel: DocumentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.get(argKeyDocument) as Document?)?.let {
            binding.title.text = it.title
            binding.text.setText(it.text)
        }
    }
}