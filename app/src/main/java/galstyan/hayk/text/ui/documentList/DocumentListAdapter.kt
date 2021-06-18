package galstyan.hayk.text.ui.documentList

import android.view.LayoutInflater
import android.view.ViewGroup
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.databinding.ItemDocumentBinding
import galstyan.hayk.text.ui.BaseListAdapter
import galstyan.hayk.text.ui.BoundViewHolder


class DocumentListAdapter : BaseListAdapter<Document>(DocumentListItemDiffer()) {
    override
    fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BoundViewHolder<Document> =
        DocumentViewHolder(ItemDocumentBinding.inflate(inflater, parent, false))

}

