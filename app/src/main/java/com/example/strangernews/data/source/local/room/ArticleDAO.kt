package com.example.strangernews.data.source.local.room

import androidx.room.*
import com.example.strangernews.data.model.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM tb_article")
    fun getAllTrack(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}
