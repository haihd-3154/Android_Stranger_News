package com.example.strangernews.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.strangernews.data.model.Article

@Database(entities = [Article::class], exportSchema = false, version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDAO
}
