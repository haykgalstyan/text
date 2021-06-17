package galstyan.hayk.text.ui.documentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.core.domain.usecase.DocumentListGet
import galstyan.hayk.core.domain.usecase.DocumentSave
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentListViewModel @Inject constructor(
    private val documentListGet: DocumentListGet,
) : ViewModel() {

    private val _documentListObservable = MutableLiveData<List<Document>>()
    val documentListObservable: LiveData<List<Document>> get() = _documentListObservable

    fun getDocumentList() {
        viewModelScope.launch {
            _documentListObservable.value = documentListGet.invoke()
        }
    }
}