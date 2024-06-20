package com.adriyo.newsapp.di

import com.adriyo.newsapp.core.network.model.ArticleMapper
import com.adriyo.newsapp.core.network.api.NewsApi
import com.adriyo.newsapp.core.domain.NewsRemoteDataSource
import com.adriyo.newsapp.core.repository.NewsRepositoryImpl
import com.adriyo.newsapp.shared.util.CoroutineDispatchers
import com.adriyo.newsapp.shared.util.DefaultCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatchers {
        return DefaultCoroutineDispatchers()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepositoryImpl {
        return NewsRepositoryImpl(remoteDataSource = newsRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsApi: NewsApi): NewsRemoteDataSource {
        return NewsRemoteDataSource(
            api = newsApi,
            entityMapper = ArticleMapper(),
        )
    }

}