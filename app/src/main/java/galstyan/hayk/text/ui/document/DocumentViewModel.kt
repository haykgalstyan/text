package galstyan.hayk.text.ui.document

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.core.domain.usecase.DocumentRemove
import galstyan.hayk.core.domain.usecase.DocumentSave
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentSave: DocumentSave,
    private val documentRemove: DocumentRemove,
) : ViewModel() {

    fun saveDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            documentSave.invoke(document)
        }
    }

    fun removeDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            documentRemove.invoke(document)
        }
    }
}