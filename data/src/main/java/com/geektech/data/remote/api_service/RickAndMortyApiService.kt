package com.geektech.data.remote.api_service

import com.geektech.data.remote.models.character.RickAndMortyResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int?,
        @Query("status") status: Int?
    ) : RickAndMortyResponseDto
}