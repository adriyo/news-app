package com.adriyo.newsapp.core.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.network.helper.getError

class NewsSearchPagingSource(
    private val query: String,
    private val dataSource: NewsRemoteDataSource
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), null, null)
        }
        try {
            val nextPageNumber = params.key ?: 1
            val response = dataSource.searchNews(
                query = query,
                page = nextPageNumber,
                pageSize = 20
            )
            if (response.isFailure) {
                return LoadResult.Error(response.exceptionOrNull() ?: Exception())
            }
            val data = response.getOrNull() ?: emptyList()
            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e.getError())
        }
    }
}