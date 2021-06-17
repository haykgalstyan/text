package galstyan.hayk.core.domain.usecase

import galstyan.hayk.core.data.DocumentRepository

class DocumentListGet(private val repository: DocumentRepository) {
    suspend operator fun invoke() = repository.getList()
}