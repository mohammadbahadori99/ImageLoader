package com.example.imageloader


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WebService {
    @GET("/assets")
    suspend fun fetAllImages(): MyResponse
}