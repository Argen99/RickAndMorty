package com.geektech.data.remote.paging_src

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geektech.data.core.utils.mappers.toModel
import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.domain.models.characters.Character
import com.geektech.domain.models.episodes.Episodes
import com.geektech.domain.models.location.Location

class LocationPagingSource(
    private val apiService: RickAndMortyApiService,
    private val name: String?,
    ) : PagingSource<Int, Location>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllLocations(
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

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}