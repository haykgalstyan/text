package galstyan.hayk.text.ui.documentList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.usecase.DocumentListGet
import galstyan.hayk.core.domain.usecase.DocumentSave
import javax.inject.Inject

@HiltViewModel
class DocumentListViewModel @Inject constructor(
    documentListGet: DocumentListGet,
) : ViewModel() {


}