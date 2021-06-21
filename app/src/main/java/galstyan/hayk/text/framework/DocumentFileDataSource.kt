package galstyan.hayk.text.framework

import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.Logger
import java.io.File

class DocumentFileDataSource(
    private val workingDirectory: File,
    private val logger: Logger,
) : DocumentDataSource {

    init {
        if (!workingDirectory.isDirectory) {
            logger.log(javaClass.name, "$workingDirectory is not a directory")
        }
    }

    override suspend fun getList(): List<Document> {
        val files = workingDirectory.listFiles() ?: return emptyList()
        return files.map {
            Document(
                title = it.name,
                text = it.readText(),
                dateCreated = 0L,
                dateEdited = 0L
            )
        }
    }

    override suspend fun save(document: Document) {
        val file = File(workingDirectory, document.title)
        if (!file.exists()) file.createNewFile()
        file.writeText(document.text)
    }

    override suspend fun remove(document: Document) {
        val file = File(workingDirectory, document.title)
        if (file.exists()) file.delete()
    }
}