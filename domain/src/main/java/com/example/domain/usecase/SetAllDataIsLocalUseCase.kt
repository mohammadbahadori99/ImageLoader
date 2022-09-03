package com.example.domain.usecase

import com.example.domain.repository.ImageRepository
import javax.inject.Inject

class SetAllDataIsLocalUseCase @Inject constructor(val imageRepository: ImageRepository) {
    suspend operator fun invoke() {
        imageRepository.setAllDataIsLocal()
    }
}