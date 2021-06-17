package galstyan.hayk.core.data

import galstyan.hayk.core.domain.entity.Document

interface DocumentDataSource {

    suspend fun getList(): List<Document>

    suspend fun save(document: Document)

    suspend fun remove(document: Document)
}