package galstyan.hayk.text.ui.documentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.Logger
import galstyan.hayk.text.R
import galstyan.hayk.text.ui.ViewBindingFragment
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
        configureListUI()
        setUpListRefresh()
        binding.toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }

        val adapter = createDocumentListAdapter(::openDocument)
        binding.documentList.adapter = adapter

        viewModel.documentListObservable.observe(viewLifecycleOwner, { documentList ->
            updateList(adapter, documentList)
        })

        binding.createNoteAction.setOnClickListener { openDocument() }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createNoteMenuAction -> openDocument()
        }
        return true
    }


    private fun setUpListRefresh() = binding.documentListRefresh.apply {
        setOnRefreshListener {
            isRefreshing = false
            viewModel.getDocumentList()
        }
    }


    private fun updateList(adapter: BaseListAdapter<Document>, documentList: List<Document>) {
        adapter.submitList(documentList)
        binding.documentList.invalidateItemDecorations()
    }


    private fun openDocument(document: Document? = null) {
        push(DocumentFragment.newInstance(document))
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


    private fun configureListUI() = binding.documentList.apply {
        addItemMarginsFirst(top = toPx(16))
        addItemMargins(
            top = toPx(8),
            left = toPx(16),
            right = toPx(16)
        )
        addItemMarginsLast(bottom = toPx(16))
    }
}
