package com.example.imageloader

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(): LiveData<MyResponse> {
        return imageRepository.fetchAllImages()
    }
}