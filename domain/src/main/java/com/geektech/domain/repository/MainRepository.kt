package com.geektech.domain.repository

import androidx.paging.PagingData
import com.geektech.domain.models.characters.Character
import com.geektech.domain.models.episodes.Episodes
import com.geektech.domain.models.location.Location
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllCharacters(name: String?,status: String?,
                         species: String?,gender: String?): Flow<PagingData<Character>>

    fun getAllEpisodes(name: String?): Flow<PagingData<Episodes>>

    fun getAllLocations(name: String?): Flow<PagingData<Location>>
}