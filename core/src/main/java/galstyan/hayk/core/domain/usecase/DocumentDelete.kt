package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.DocumentRepository
import galstyan.hayk.core.domain.entity.Document

class DocumentRemove(private val repository: DocumentRepository) {
    suspend operator fun invoke(document: Document) = repository.remove(document)
}