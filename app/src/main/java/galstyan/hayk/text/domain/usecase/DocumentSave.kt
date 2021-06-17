package galstyan.hayk.text.domain.usecase

import galstyan.hayk.text.data.repository.DocumentRepository
import galstyan.hayk.text.domain.entity.Document

class DocumentSave(private val repository: DocumentRepository) {
    suspend operator fun invoke(document: Document) {
        repository.save(document)
    }
}