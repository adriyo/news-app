package com.adriyo.newsapp.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adriyo.newsapp.core.domain.model.Article

class ErrorPagingSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return LoadResult.Error(Exception("Error"))
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}