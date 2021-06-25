package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.DocumentRepository
import galstyan.hayk.core.domain.entity.Document


class DocumentListReorder(private val repository: DocumentRepository) {
    suspend operator fun invoke(
        documentList: List<Document>
    ) = repository.saveListOrder(documentList)
}