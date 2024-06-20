package com.adriyo.newsapp.core.repository

import androidx.paging.PagingData
import com.adriyo.newsapp.core.domain.model.Category
import com.adriyo.newsapp.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsStream(category: Category): Flow<PagingData<Article>>
    fun searchNewsStream(query: String): Flow<PagingData<Article>>
    suspend fun getTopHeadlines(): List<Article>
}