package com.adriyo.newsapp.core

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.domain.model.Source
import com.adriyo.newsapp.core.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class FakeNewsRepository : NewsRepository {

    val topHeadlinesArticles = (0..2).map {
        Article(
            source = Source(id = null, name = "source $it"),
            author = null,
            title = "Title $it",
            description = "Description $it",
            url = "",
            urlToImage = null,
            publishedAt = "",
            content = null
        )
    }
    var currentCategory = Category.BUSINESS
    val articles = listOf(
        Article(
            source = Source(id = null, name = ""),
            author = null,
            title = "",
            description = null,
            url = "",
            urlToImage = null,
            publishedAt = "",
            content = null
        )
    )

    private val pagingSourceFactory = articles.asPagingSourceFactory()
    private val emptyPagingSourceFactory = emptyList<Article>().asPagingSourceFactory()

    override fun getNewsStream(category: Category): Flow<PagingData<Article>> {
        this.currentCategory = category
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                pagingSourceFactory()
            }
        ).flow
    }

    override fun searchNewsStream(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                if (query.isEmpty()) {
                    emptyPagingSourceFactory()
                } else {
                    pagingSourceFactory()
                }
            }
        ).flow
    }

    override suspend fun getTopHeadlines(): List<Article> {
        return topHeadlinesArticles
    }
}
