package com.example.strangernews.data.di

import com.example.strangernews.data.source.ArticleDataSource
import com.example.strangernews.data.source.local.ArticleLocalImpl
import com.example.strangernews.data.source.local.room.ArticleDAO
import com.example.strangernews.data.source.remote.ArticleRemoteImpl
import com.example.strangernews.data.source.remote.api.ApiService
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DataSourceModule {

    @Single
    fun provideLocalArticlesDataSource(dao: ArticleDAO): ArticleDataSource.Local =
        ArticleLocalImpl(dao)

    @Single
    fun provideRemoteArticlesDataSource(apiService: ApiService): ArticleDataSource.Remote =
        ArticleRemoteImpl(apiService)
}
