package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.DocumentRepository
import galstyan.hayk.core.domain.entity.Document

class DocumentSave(private val repository: DocumentRepository) {
    suspend operator fun invoke(document: Document) {
        repository.save(document)
    }
}