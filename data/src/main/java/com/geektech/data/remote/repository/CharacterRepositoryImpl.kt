package com.geektech.data.remote.repository

import com.geektech.data.core.base.BaseRepository
import com.geektech.data.core.utils.mappers.toModel
import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.domain.common.Resource
import com.geektech.domain.models.RickAndMortyResponse
import com.geektech.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApiService
): BaseRepository(), CharacterRepository {

    override fun getAllCharacters(page: Int?, status: String?): Flow<Resource<RickAndMortyResponse>> = doRequest {
        apiService.getAllCharacters(page,status).toModel()
    }
}