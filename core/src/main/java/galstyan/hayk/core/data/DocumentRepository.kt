package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.Document


class DocumentRepository(private val dataSource: DocumentDataSource) {

    suspend fun getList(): List<Document> = dataSource.getList()

    suspend fun save(document: Document) = dataSource.save(document)
}