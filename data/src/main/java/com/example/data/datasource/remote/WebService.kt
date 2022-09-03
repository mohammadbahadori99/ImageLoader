package com.example.data.datasource.remote


import com.example.data.datasource.model.MyResponseDTO

interface WebService {
    @retrofit2.http.GET("/assets")
    suspend fun fetAllImages(): MyResponseDTO
}