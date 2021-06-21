package galstyan.hayk.text.framework

import android.content.Context
import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.domain.entity.Document
import galstyan.hayk.text.R
import javax.inject.Inject

class DocumentMockDataSource @Inject constructor(
    private val context: Context
) : DocumentDataSource {

    override suspend fun getList(): List<Document> {
        val list = mutableListOf<Document>()
        repeat(50) {
            list.add(
                Document(
                    title = "Document $it",
                    text = context.getString(R.string.dummy_text_long),
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