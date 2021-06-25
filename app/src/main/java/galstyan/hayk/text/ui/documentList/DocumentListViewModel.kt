package galstyan.hayk.text.ui.documentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.core.domain.usecase.DocumentListGet
import galstyan.hayk.core.domain.usecase.DocumentListReorder
import galstyan.hayk.text.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DocumentListViewModel @Inject constructor(
    private val documentListGet: DocumentListGet,
    private val documentListReorder: DocumentListReorder,
    private val logger: Logger
) : ViewModel() {

    private val _documentListObservable = MutableLiveData<List<Document>>()
    val documentListObservable: LiveData<List<Document>> get() = _documentListObservable

    private var isReordered = false

    fun getDocumentList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isReordered) {
                saveListOrder()
            }
            _documentListObservable.postValue(documentListGet.invoke())
        }
    }


    fun saveListOrder() {
        if (!isReordered) return

        val list = _documentListObservable.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            saveListOrder(list)
        }
    }


    fun onItemMove(from: Int, to: Int) {
        isReordered = true

        val list = _documentListObservable.value?.toMutableList() ?: return
        Collections.swap(list, from, to)
        _documentListObservable.value = list
    }


    private suspend fun saveListOrder(list: List<Document>) {
        documentListReorder.invoke(list)
        isReordered = false
    }
}