package com.geektech.data.remote.api_service

import com.geektech.data.remote.models.character.CharacterResponseDto
import com.geektech.data.remote.models.episode.EpisodesResponseDto
import com.geektech.data.remote.models.location.LocationResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("gender") gender: String?
    ) : CharacterResponseDto

    @GET("api/episode")
    suspend fun getAllEpisodes(
        @Query("page") page: Int?,
        @Query("name") name: String?,
    ) : EpisodesResponseDto

    @GET("api/location")
    suspend fun getAllLocations(
        @Query("page") page: Int?,
        @Query("name") name: String?,
    ) : LocationResponseDto
}