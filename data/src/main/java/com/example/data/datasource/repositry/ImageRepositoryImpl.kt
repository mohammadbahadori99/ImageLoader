package com.example.data.datasource.repositry

import androidx.room.withTransaction
import com.example.data.datasource.ImageDatabase
import com.example.data.datasource.local.ImageDao
import com.example.data.datasource.model.toImageDataDomainModel
import com.example.data.datasource.model.toImageDataEntity
import com.example.data.datasource.model.toMyResponseDomainModel
import com.example.data.datasource.remote.WebService
import com.example.domain.model.ImageDataDomainModel
import com.example.domain.model.MyResponseDomainModel
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val dao: ImageDao,
    private val imageDatabase: ImageDatabase
) : ImageRepository {
    override suspend fun fetchAllImages(): Flow<MyResponseDomainModel> {
        return flowOf(webService.fetAllImages().toMyResponseDomainModel())

    }

    override suspend fun insertAllImages(list: List<ImageDataDomainModel>): List<Long> {
        return dao.insertAllImages(list.map { it.toImageDataEntity() })
    }

    override suspend fun setSoftEntryFalse() {
        dao.setSoftDeleteTrue()
    }

    override suspend fun getAllImageData(): List<ImageDataDomainModel> {
        return dao.getAllImageData().map { it.toImageDataDomainModel() }
    }

    override suspend fun updateAllImages(list: List<ImageDataDomainModel>) {
        imageDatabase.withTransaction {
            dao.updateList(list.map { it.toImageDataEntity() })
        }
    }

    override suspend fun setAllDataIsLocal() {
        dao.setAllDataIsLocal()
    }
}