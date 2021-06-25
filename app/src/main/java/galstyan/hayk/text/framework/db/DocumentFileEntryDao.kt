package galstyan.hayk.text.framework.db

import androidx.room.*

@Dao
interface DocumentFileEntryDao {
    @Query("SELECT * FROM ${DocumentEntity.tableName}")
    fun getAll(): List<DocumentEntity>

    @Query("SELECT * FROM ${DocumentEntity.tableName} WHERE id=:id ")
    fun get(id: Int): DocumentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(documentEntity: DocumentEntity): Long

    @Update
    fun update(documentEntity: DocumentEntity)

    @Update
    fun update(documentEntity: List<DocumentEntity>)

    @Update(entity = DocumentEntity::class)
    fun updateOrder(updates: List<DocumentEntityUpdateOrder>)

    @Delete
    fun delete(documentEntity: DocumentEntity)
}