package com.example.strangernews.data.di

import android.app.Application
import androidx.room.Room
import com.example.strangernews.data.source.local.room.ArticleDAO
import com.example.strangernews.data.source.local.room.NewsDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class RoomModule {

    @Single
    fun provideDatabases(application: Application): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()

    @Single
    fun provideArticleDAO(newsDatabase: NewsDatabase): ArticleDAO = newsDatabase.articleDao()
}
