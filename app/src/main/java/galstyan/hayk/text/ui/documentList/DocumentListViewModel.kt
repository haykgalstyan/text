package galstyan.hayk.text.ui.documentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.core.domain.usecase.DocumentListGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DocumentListViewModel @Inject constructor(
    private val documentListGet: DocumentListGet,
) : ViewModel() {

    private val _documentListObservable = MutableLiveData<List<Document>>()
    val documentListObservable: LiveData<List<Document>> get() = _documentListObservable

    fun getDocumentList() {
        viewModelScope.launch(Dispatchers.IO) {
            _documentListObservable.postValue(documentListGet.invoke())
        }
    }

    fun onItemMove(from: Int, to: Int) {
        val list = _documentListObservable.value ?: return
        Collections.swap(list, from, to)
        _documentListObservable.value = list
    }
}