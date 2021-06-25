package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.Document

interface DocumentDataSource {

    suspend fun getList(): List<Document>

    /**
     * Create a new document if it does not exist
     * Otherwise replace the one with the matching name
     */
    suspend fun save(document: Document)

    /**
     * Remove the document if it exists
     */
    suspend fun remove(document: Document)


    /**
     * Reorder stored documents by positions in passed list
     */
    suspend fun saveListOrder(documentList: List<Document>)
}