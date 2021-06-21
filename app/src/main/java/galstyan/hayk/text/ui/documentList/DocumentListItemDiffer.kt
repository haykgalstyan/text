package galstyan.hayk.text.ui.documentList

import androidx.recyclerview.widget.DiffUtil
import galstyan.hayk.core.domain.entity.Document


class DocumentListItemDiffer : DiffUtil.ItemCallback<Document>() {
    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem == newItem
    }
}