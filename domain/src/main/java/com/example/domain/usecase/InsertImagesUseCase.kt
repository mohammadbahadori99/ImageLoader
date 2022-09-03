package com.example.domain.usecase

import com.example.domain.model.ImageDataDomainModel
import com.example.domain.repository.ImageRepository
import javax.inject.Inject

class InsertImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(list: List<ImageDataDomainModel>): List<Long> {
        imageRepository.setSoftEntryFalse()
        val insertStatus = imageRepository.insertAllImages(list)
        val notInsertedWithIndex = insertStatus.withIndex().filter { it.value.toInt() == -1 }
        val notInsertedItems = mutableListOf<ImageDataDomainModel>()
        notInsertedWithIndex.forEach {
            notInsertedItems.add(
                list[it.index].copy(
                    isNewItem = false,
                    existedItem = true,
                    isSoftDeleted = false
                )
            )
        }
        if (notInsertedItems.size > 0)
            notInsertedItems[0].stats.dev = 5
        imageRepository.updateAllImages(notInsertedItems)
        return insertStatus
    }
}