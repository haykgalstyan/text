package galstyan.hayk.text.ui.documentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.text.Logger
import galstyan.hayk.text.R
import galstyan.hayk.text.ViewBindingFragment
import galstyan.hayk.text.databinding.FragmentDocumentListBinding
import javax.inject.Inject

@AndroidEntryPoint
class DocumentListFragment : ViewBindingFragment<FragmentDocumentListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDocumentListBinding =
        FragmentDocumentListBinding::inflate

    @Inject
    lateinit var logger: Logger

    private val viewModel: DocumentListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDocumentList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DocumentListAdapter()
        binding.documentList.adapter = adapter

        viewModel.documentListObservable.observe(viewLifecycleOwner, { documentList ->
            if (documentList.isNotEmpty())
                adapter.submitList(documentList)
            else logger.log("doc", "none")
        })
    }
}