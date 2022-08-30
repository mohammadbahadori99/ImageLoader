package com.example.imageloader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val webService: WebService,
    private val dao: ImageDao,
    private val imageDatabase: ImageDatabase
) {
    suspend fun fetchAllImages(): LiveData<MyResponse> {
        return MutableLiveData(webService.fetAllImages())

    }

    suspend fun insertAllImages(list: List<ImageData>): List<Long> {
        return dao.insertAllImages(list)
    }

    suspend fun setSoftEntryFalse() {
        dao.setSoftDeleteTrue()
    }

    suspend fun getAllImageData(): List<ImageData> {
        return dao.getAllImageData()
    }

    suspend fun updateAllImages(list: List<ImageData>) {
        imageDatabase.withTransaction {
            dao.updateList(list)
        }
    }
}