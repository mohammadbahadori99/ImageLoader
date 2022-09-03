package com.example.domain.usecase

import com.example.domain.model.MyResponseDomainModel
import com.example.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(): Flow<MyResponseDomainModel> {
        return imageRepository.fetchAllImages()
    }
}