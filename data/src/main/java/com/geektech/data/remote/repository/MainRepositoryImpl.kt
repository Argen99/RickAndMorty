package com.geektech.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.data.remote.paging_src.CharacterPagingSource
import com.geektech.data.remote.paging_src.EpisodePagingSource
import com.geektech.data.remote.paging_src.LocationPagingSource
import com.geektech.domain.models.characters.Character
import com.geektech.domain.models.episodes.Episodes
import com.geektech.domain.models.location.Location
import com.geektech.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: RickAndMortyApiService
): BaseRepository(), MainRepository {

    override fun getAllCharacters(name: String? ,status: String?,species: String?,gender: String?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiService = apiService, name = name,
                    status = status, species = species, gender = gender)
            }
        ).flow
    }

    override fun getAllEpisodes(name: String?): Flow<PagingData<Episodes>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                EpisodePagingSource(apiService = apiService, name = name)
            }
        ).flow
    }

    override fun getAllLocations(name: String?): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                LocationPagingSource(apiService = apiService, name = name)
            }
        ).flow
    }
}