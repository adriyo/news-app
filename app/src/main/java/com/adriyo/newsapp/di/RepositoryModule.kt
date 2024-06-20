package com.adriyo.newsapp.di

import com.adriyo.newsapp.core.repository.NewsRepository
import com.adriyo.newsapp.core.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

   @Binds
   abstract fun bindsNewsRepository(
       repository: NewsRepositoryImpl
   ): NewsRepository

}