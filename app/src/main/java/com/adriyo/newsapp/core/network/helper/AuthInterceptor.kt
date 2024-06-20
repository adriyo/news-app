package com.adriyo.newsapp.core.network.helper

import com.adriyo.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val accessToken = BuildConfig.API_KEY
        if (accessToken.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", accessToken)
        }
        return chain.proceed(requestBuilder.build())
    }

}