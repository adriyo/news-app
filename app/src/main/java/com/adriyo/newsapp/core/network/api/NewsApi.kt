package com.adriyo.newsapp.core.network.api

import com.adriyo.newsapp.core.network.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int ,
        @Query("page") page: Int,
        @Query("category") category: String? = null,
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse
}
