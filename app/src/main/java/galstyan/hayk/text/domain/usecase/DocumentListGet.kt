package galstyan.hayk.text.domain.usecase

import galstyan.hayk.text.data.repository.DocumentRepository

class DocumentListGet(private val repository: DocumentRepository) {
    suspend operator fun invoke() {
        repository.getList()
    }
}