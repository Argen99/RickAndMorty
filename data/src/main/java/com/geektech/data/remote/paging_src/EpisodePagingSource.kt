package com.geektech.data.remote.paging_src

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geektech.data.core.utils.mappers.toModel
import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.domain.models.characters.Character
import com.geektech.domain.models.episodes.Episodes

class EpisodePagingSource(
    private val apiService: RickAndMortyApiService,
    private val name: String?,
    ) : PagingSource<Int, Episodes>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episodes> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllEpisodes(
                page = page, name = name).toModel()

            val nextPageNumber = if (response.info.next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")?.toInt()
            }

            LoadResult.Page(
                data = response.results,
                nextKey = nextPageNumber,
                prevKey = null)

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episodes>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}