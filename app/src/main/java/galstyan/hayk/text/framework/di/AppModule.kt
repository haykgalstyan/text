package galstyan.hayk.text.framework.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.core.data.DocumentDataSource
import galstyan.hayk.core.data.DocumentRepository
import galstyan.hayk.core.domain.usecase.DocumentListGet
import galstyan.hayk.core.domain.usecase.DocumentSave
import galstyan.hayk.text.Logger
import galstyan.hayk.text.framework.DocumentFileDataSource
import galstyan.hayk.text.framework.DocumentMockDataSource
import galstyan.hayk.text.framework.log.NamedAndroidDebugLogger
import java.io.File


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogger(): Logger = NamedAndroidDebugLogger("AppLogger")

    @Provides
    fun provideDocumentDataSource(@ApplicationContext context: Context): DocumentDataSource =
        DocumentFileDataSource(context.filesDir, provideLogger())

    @Provides
    fun provideDocumentRepository(documentDataSource: DocumentDataSource) =
        DocumentRepository(documentDataSource)

    @Provides
    fun provideDocumentListGet(documentRepository: DocumentRepository): DocumentListGet =
        DocumentListGet(documentRepository)

    @Provides
    fun provideDocumentSave(documentRepository: DocumentRepository): DocumentSave =
        DocumentSave(documentRepository)
}