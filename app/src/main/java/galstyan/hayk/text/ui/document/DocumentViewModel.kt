package galstyan.hayk.text.ui.document

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.core.domain.usecase.DocumentRemove
import galstyan.hayk.core.domain.usecase.DocumentSave
import galstyan.hayk.text.Logger
import galstyan.hayk.text.ui.util.toEditable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val documentSave: DocumentSave,
    private val documentRemove: DocumentRemove,
    private val logger: Logger,
) : ViewModel() {

    val initialDocument: Document? = savedStateHandle[DocumentFragment.argKeyDocument]

    var title: Editable? = initialDocument?.title.toEditable()
    var text: Editable? = initialDocument?.text.toEditable()

    private var isDeleted = false


    init {
        logger.log(javaClass.simpleName, initialDocument.toString())
    }

    fun saveDocument() {
        if (isDeleted) {
            logger.log(javaClass.simpleName + "saveDocument", "Already deleted")
            return
        }
        if (initialDocument == null && title.isNullOrBlank() && text.isNullOrBlank()) {
            logger.log(javaClass.simpleName + "saveDocument", "Nothing to save")
            return
        }

        val document = (initialDocument ?: Document()).copy(
            title = title.toString(),
            text = text.toString()
        )
        logger.log(javaClass.simpleName, "Saving $document")
        viewModelScope.launch(Dispatchers.IO) {
            documentSave.invoke(document)
        }
    }

    fun removeDocument() {
        isDeleted = true
        initialDocument?.let {
            viewModelScope.launch(Dispatchers.IO) {
                documentRemove.invoke(it)
            }
        }
    }
}