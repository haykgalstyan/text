package com.example.tasks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.notes.Logger
import galstyan.hayk.notes.log.NamedAndroidDebugLogger


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLogger(): Logger = NamedAndroidDebugLogger("AppLogger")
}