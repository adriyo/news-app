package com.adriyo.newsapp.di

import android.content.Context
import com.adriyo.newsapp.BuildConfig
import com.adriyo.newsapp.core.network.helper.AuthInterceptor
import com.adriyo.newsapp.core.network.api.NewsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)
        val timeout = 120L
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val format = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(format.asConverterFactory(contentType))
            .build()
    }
}