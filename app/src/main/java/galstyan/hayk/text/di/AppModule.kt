package galstyan.hayk.text.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.text.Logger
import galstyan.hayk.text.log.NamedAndroidDebugLogger


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogger(): Logger = NamedAndroidDebugLogger("AppLogger")
}