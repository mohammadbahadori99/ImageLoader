package com.example.data.datasource.di

import com.example.data.datasource.repositry.ImageRepositoryImpl
import com.example.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindImageRepository(imageRepository: ImageRepositoryImpl): ImageRepository
}