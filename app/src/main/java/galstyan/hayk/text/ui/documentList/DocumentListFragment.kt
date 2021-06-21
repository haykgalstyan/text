package galstyan.hayk.text.ui.documentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.Logger
import galstyan.hayk.text.ViewBindingFragment
import galstyan.hayk.text.databinding.FragmentDocumentListBinding
import galstyan.hayk.text.databinding.ItemDocumentBinding
import galstyan.hayk.text.ui.*
import galstyan.hayk.text.ui.document.DocumentFragment
import galstyan.hayk.text.ui.push
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

        val adapter = createDocumentListAdapter(onClick = {
            push(DocumentFragment.newInstance(it))
        }).also { binding.documentList.adapter = it }

        binding.documentList.apply {
            addItemMargins(
                top = toPx(8),
                left = toPx(16),
                right = toPx(16)
            )
            addItemMarginsLast(
                bottom = toPx(16)
            )
        }

        binding.createNoteAction.setOnClickListener {
            push(DocumentFragment())
        }

        viewModel.documentListObservable.observe(viewLifecycleOwner, { documentList ->
            if (documentList.isNotEmpty())
                adapter.submitList(documentList)
            else logger.log("doc", "none")
        })
    }

    private fun createDocumentListAdapter(onClick: (Document) -> Unit): BaseListAdapter<Document> =
        object : BaseListAdapter<Document>(DocumentListItemDiffer()) {
            override
            fun createViewHolder(
                inflater: LayoutInflater,
                parent: ViewGroup,
                viewType: Int
            ): BoundViewHolder<Document> {
                val binding = ItemDocumentBinding.inflate(inflater, parent, false)
                return object : BoundViewHolder<Document>(binding.root) {
                    init {

                    }

                    override fun bind(it: Document) {
                        binding.title.text = it.title
                        binding.text.text = it.text
                        binding.root.setOnClickListener {
                            onClick.invoke(getItem(adapterPosition))
                        }
                    }
                }
            }
        }
}
