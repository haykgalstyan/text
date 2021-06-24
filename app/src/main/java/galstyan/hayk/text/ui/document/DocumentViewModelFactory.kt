package galstyan.hayk.text.ui.document

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import galstyan.hayk.core.domain.entity.Document


class DocumentViewModelFactory(initialDocument: Document?) : ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DocumentViewModel() as T
}