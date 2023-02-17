package com.geektech.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geektech.data.core.base.BaseRepository
import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.data.remote.paging_src.CharactersPagingSource
import com.geektech.domain.models.characters.Character
import com.geektech.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApiService
): BaseRepository(), CharacterRepository {

//    override fun getAllCharacters(page: Int?, status: String?): Flow<PagingData<Character>> = doPagingRequest(
//        CharactersPagingSource(
//            apiService, status
//        )
//    ).map { data ->
//        data.map { it.toModel() }
//    }

    override fun getPagingData(name: String? ,status: String?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService = apiService, name = name, status = status)
            }
        ).flow
    }
}