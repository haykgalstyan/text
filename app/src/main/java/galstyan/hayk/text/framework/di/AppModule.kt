package galstyan.hayk.text.framework.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.data.DocumentRepository
import galstyan.hayk.core.domain.usecase.DocumentListGet
import galstyan.hayk.core.domain.usecase.DocumentListReorder
import galstyan.hayk.core.domain.usecase.DocumentRemove
import galstyan.hayk.core.domain.usecase.DocumentSave
import galstyan.hayk.text.Logger
import galstyan.hayk.text.framework.DocumentFileDataSource
import galstyan.hayk.text.framework.db.AppDatabase
import galstyan.hayk.text.framework.log.NamedAndroidDebugLogger


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogger(): Logger = NamedAndroidDebugLogger("AppLogger")

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase::class.java.name)
            .build()

    @Provides
    fun provideDocumentDataSource(@ApplicationContext context: Context): DocumentDataSource =
        DocumentFileDataSource(provideDatabase(context), context.filesDir, provideLogger())

    @Provides
    fun provideDocumentRepository(documentDataSource: DocumentDataSource) =
        DocumentRepository(documentDataSource)

    @Provides
    fun provideDocumentListGet(documentRepository: DocumentRepository): DocumentListGet =
        DocumentListGet(documentRepository)

    @Provides
    fun provideDocumentSave(documentRepository: DocumentRepository): DocumentSave =
        DocumentSave(documentRepository)

    @Provides
    fun provideDocumentRemove(documentRepository: DocumentRepository): DocumentRemove =
        DocumentRemove(documentRepository)

    @Provides
    fun provideDocumentListReorder(documentRepository: DocumentRepository): DocumentListReorder =
        DocumentListReorder(documentRepository)
}