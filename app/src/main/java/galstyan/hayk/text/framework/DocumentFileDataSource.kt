package galstyan.hayk.text.framework

import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.Logger
import galstyan.hayk.text.framework.db.AppDatabase
import galstyan.hayk.text.framework.db.DocumentEntity
import galstyan.hayk.text.framework.db.DocumentEntityUpdateOrder
import java.io.File


class DocumentFileDataSource(
    database: AppDatabase,
    private val workingDirectory: File,
    private val logger: Logger,
) : DocumentDataSource {

    private val dao = database.documentFileEntryDao()

    init {
        logger.log(
            javaClass.simpleName,
            if (!workingDirectory.isDirectory)
                "$workingDirectory is not a directory"
            else "Files in directory \"${workingDirectory.name}\":\n${
                workingDirectory.listFiles()?.map { it.name }
            }"
        )
    }


    override suspend fun getList() = dao.getAll().mapNotNull { dbEntry ->
        val fileName = dbEntry.fileName ?: return@mapNotNull null
        val file = File(workingDirectory, fileName)
        if (!file.exists() || file.isDirectory) {
            logger.log(
                javaClass.simpleName,
                "File with name \"${dbEntry.fileName}\" not found while getList()"
            )
            return@mapNotNull null
        }
        Document(
            id = dbEntry.id,
            title = dbEntry.documentTitle,
            text = file.readText(),
            timeCreated = dbEntry.created,
            timeEdited = dbEntry.edited,
            orderIndex = dbEntry.orderIndex,
        )
    }


    override suspend fun save(document: Document) {
        if (document.title.isNullOrBlank() && document.text.isNullOrBlank())
            return

        val timeStamp = System.currentTimeMillis()
        val id = document.id
        val documentTitle = document.title?.takeIf { it.isNotBlank() }
        val contentText = document.text?.takeIf { it.isNotBlank() }
        val creationTime = document.timeCreated ?: timeStamp
        val editionTime = document.timeCreated ?: timeStamp
        val fileName = documentTitle ?: timeStamp.toString()
        val file = File(workingDirectory, fileName)

        if (!file.exists() && !file.isDirectory) file.createNewFile()
        contentText?.let { file.writeText(it) }
        dao.insert(
            DocumentEntity(
                id = id,
                documentTitle = documentTitle,
                created = creationTime,
                edited = editionTime,
                fileName = fileName,
                orderIndex = Int.MAX_VALUE
            )
        )
    }


    override suspend fun remove(document: Document) {
        val id = document.id ?: return
        val documentEntity = dao.get(id)

        logger.log(
            javaClass.simpleName,
            "\nbefore remove $documentEntity...\n" +
                    "files in dir: ${workingDirectory.name}\n" +
                    "${workingDirectory.listFiles()?.map { it.name }}"
        )

        dao.delete(documentEntity)

        val fileName = documentEntity.fileName ?: return
        val file = File(workingDirectory, fileName)
        if (file.exists()) file.delete()

        logger.log(
            javaClass.simpleName,
            "\nremove $documentEntity...\n" +
                    "files in dir: ${workingDirectory.name}\n" +
                    "${workingDirectory.listFiles()?.map { it.name }}"
        )
    }


    override suspend fun saveListOrder(documentList: List<Document>) {
        val entities = documentList.mapIndexed { index, document ->
            DocumentEntityUpdateOrder(
                id = document.id,
                orderIndex = index
            )
        }
        dao.updateOrder(entities)
    }


    private fun clearWorkingDirectory() {
        workingDirectory.listFiles()?.forEach { it.delete() }
    }
}