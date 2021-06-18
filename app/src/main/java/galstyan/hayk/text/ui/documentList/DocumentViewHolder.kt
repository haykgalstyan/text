package galstyan.hayk.text.ui.documentList

import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.databinding.ItemDocumentBinding
import galstyan.hayk.text.ui.BoundViewHolder


class DocumentViewHolder(private val binding: ItemDocumentBinding) :
    BoundViewHolder<Document>(binding.root) {

    override fun bind(it: Document) {
        binding.title.text = it.name
        binding.text.text = it.text
    }
}