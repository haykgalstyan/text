package galstyan.hayk.text.ui.document

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.core.domain.usecase.DocumentSave
import javax.inject.Inject

@HiltViewModel
class DocumentViewModel @Inject constructor(
    documentSave: DocumentSave,
) : ViewModel() {


}