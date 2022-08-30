package com.example.imageloader

import javax.inject.Inject

class ReadImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(): List<ImageData> {
        return imageRepository.getAllImageData()
    }
}