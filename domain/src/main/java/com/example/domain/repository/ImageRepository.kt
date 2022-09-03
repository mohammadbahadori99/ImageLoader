package com.example.domain.repository

import com.example.domain.model.ImageDataDomainModel
import com.example.domain.model.MyResponseDomainModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun fetchAllImages(): Flow<MyResponseDomainModel>
    suspend fun insertAllImages(list: List<ImageDataDomainModel>): List<Long>
    suspend fun setSoftEntryFalse()
    suspend fun getAllImageData(): List<ImageDataDomainModel>
    suspend fun updateAllImages(list: List<ImageDataDomainModel>)
    suspend fun setAllDataIsLocal()
}