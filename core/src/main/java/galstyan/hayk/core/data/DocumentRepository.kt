package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.Document


class DocumentRepository(private val dataSource: DocumentDataSource) {

    suspend fun getList(): List<Document> = dataSource.getList()

    /**
     * Create a new document if it does not exist
     * Otherwise replace the one with the matching name
     */
    suspend fun save(document: Document) = dataSource.save(document)
}