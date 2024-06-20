package com.adriyo.newsapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

