package com.adriyo.newsapp.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adriyo.newsapp.core.domain.NewsRemoteDataSource
import com.adriyo.newsapp.core.domain.NewsPagingSource
import com.adriyo.newsapp.core.domain.NewsSearchPagingSource
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.core.domain.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
) : NewsRepository {

    override fun getNewsStream(category: Category): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewsPagingSource(
                    category = category,
                    dataSource = remoteDataSource
                )
            }
        ).flow
    }

    override fun searchNewsStream(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewsSearchPagingSource(
                    query = query,
                    dataSource = remoteDataSource
                )
            }
        ).flow
    }

    override suspend fun getTopHeadlines(): List<Article> {
        return remoteDataSource.getTopHeadlines()
    }
}
