package galstyan.hayk.text.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = DocumentEntity.tableName)
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val created: Long? = null,
    val edited: Long? = null,
    val documentTitle: String? = null,
    val fileName: String? = null,
    val orderIndex: Int,
) {
    companion object {
        const val tableName = "document_table"
    }
}


class DocumentEntityUpdateOrder(
    val id: Int?,
    val orderIndex: Int,
)

