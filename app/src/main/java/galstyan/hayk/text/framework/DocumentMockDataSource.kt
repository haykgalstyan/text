package galstyan.hayk.text.framework

import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.Logger
import java.io.File

class DocumentMockDataSource(
    private val workingDirectory: File,
    private val logger: Logger,
) : DocumentDataSource {

    override suspend fun getList(): List<Document> {
        val list = mutableListOf<Document>()
        repeat(50) {
            list.add(
                Document(
                    name = "Document $it",
                    text = "Lololo",
                    dateCreated = 0L,
                    dateEdited = 0L,
                )
            )
        }
        return list
    }

    override suspend fun save(document: Document) {}

    override suspend fun remove(document: Document) {}
}