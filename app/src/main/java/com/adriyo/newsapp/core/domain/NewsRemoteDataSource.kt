package com.adriyo.newsapp.core.domain

import com.adriyo.newsapp.core.network.model.ArticleMapper
import com.adriyo.newsapp.core.domain.model.Article
import com.adriyo.newsapp.core.network.api.NewsApi
import com.adriyo.newsapp.core.network.helper.getError
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val api: NewsApi,
    private val entityMapper: ArticleMapper,
) {

    suspend fun fetchNews(category: String, page: Int, pageSize: Int): Result<List<Article>> {
        try {
            val response = api.getTopHeadlines(category= category, page = page, pageSize = pageSize)
            return Result.success(entityMapper.map(response.articles))
        } catch (e: Exception) {
            return Result.failure(e.getError())
        }
    }

    suspend fun searchNews(query: String, page: Int, pageSize: Int): Result<List<Article>> {
        try {
            val response = api.searchNews(query, page, pageSize)
            return Result.success(entityMapper.map(response.articles))
        } catch (e: Exception) {
            return Result.failure(e.getError())
        }
    }

    suspend fun getTopHeadlines(): List<Article> {
        try {
            val response = api.getTopHeadlines(
                page = 1,
                pageSize = 7,
                category = null
            )
            return entityMapper.map(response.articles)
        } catch (e: Exception) {
            return emptyList()
        }
    }
}

