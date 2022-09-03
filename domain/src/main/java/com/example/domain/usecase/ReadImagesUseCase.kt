package com.example.domain.usecase

import com.example.domain.model.ImageDataDomainModel
import com.example.domain.repository.ImageRepository
import javax.inject.Inject

class ReadImagesUseCase @Inject constructor(val imageRepository: ImageRepository) {
    suspend operator fun invoke(): List<ImageDataDomainModel> {
        return imageRepository.getAllImageData()
    }
}