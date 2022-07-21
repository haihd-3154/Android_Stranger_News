package com.example.strangernews.data.source.local.room

import androidx.room.*
import com.example.strangernews.data.model.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM tb_article")
    fun getAllTrack(): List<Article>

    @Query("SELECT * FROM tb_article WHERE title = :query")
    fun checkIsFavorite(query: String): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("DELETE FROM tb_article WHERE title = :query")
    suspend fun delete(query: String)
}
