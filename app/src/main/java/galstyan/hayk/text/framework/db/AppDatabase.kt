package galstyan.hayk.text.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DocumentEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun documentFileEntryDao(): DocumentFileEntryDao
}