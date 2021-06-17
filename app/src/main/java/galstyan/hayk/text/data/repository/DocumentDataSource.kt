package galstyan.hayk.text.data.repository

import galstyan.hayk.text.domain.entity.Document

interface DocumentDataSource {

    suspend fun getList(): List<Document>

    suspend fun save(document: Document)

    suspend fun remove(document: Document)
}