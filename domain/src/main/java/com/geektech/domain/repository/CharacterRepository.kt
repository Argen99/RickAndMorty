package com.geektech.domain.repository

import com.geektech.domain.common.Resource
import com.geektech.domain.models.RickAndMortyResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(page: Int?, status: String?): Flow<Resource<RickAndMortyResponse>>
}