package galstyan.hayk.text.data.repository

import galstyan.hayk.text.domain.entity.Document


class DocumentRepository(private val dataSource: DocumentDataSource) {

    suspend fun getList(): List<Document> = dataSource.getList()

    suspend fun save(document: Document) = dataSource.save(document)
}