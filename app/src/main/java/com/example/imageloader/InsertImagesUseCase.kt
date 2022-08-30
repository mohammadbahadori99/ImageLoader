package com.example.imageloader

import androidx.room.util.copy
import javax.inject.Inject

class InsertImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(list: List<ImageData>): List<Long> {
        imageRepository.setSoftEntryFalse()
        val insertStatus = imageRepository.insertAllImages(list.map {it.copy(isNewItem = true) })
        val notInsertedWithIndex = insertStatus.withIndex().filter { it.value.toInt() == -1 }
        val notInsertedItems = mutableListOf<ImageData>()
        notInsertedWithIndex.forEach {
            notInsertedItems.add(list[it.index].copy(isNewItem = false, existedItem = true, isSoftDeleted = false))
        }
        if (notInsertedItems.size>0)
        notInsertedItems[0].stats.dev = 5
        imageRepository.updateAllImages(notInsertedItems)
        return insertStatus
    }
}