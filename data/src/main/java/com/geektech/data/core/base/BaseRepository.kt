package com.geektech.data.core.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource

abstract class BaseRepository {

    protected fun <Key: Any, Model : Any> doPagingRequest(
        pagingSource: PagingSource<Key, Model>
    ) = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        ), pagingSourceFactory = {
            pagingSource
        }
    ).flow
}