package com.example.strangernews.data.source.local

import com.example.strangernews.data.source.ArticleDataSource
import com.example.strangernews.data.source.local.room.ArticleDAO
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.source.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow

class ArticleLocalImpl(
    private val dao: ArticleDAO,
) : ArticleDataSource.Local {

    override suspend fun getLocalArticles(): List<Article> {
        return dao.getAllTrack()
    }

    override suspend fun insertArticle(article: Article) {
        dao.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.delete(article.title)
    }

    override suspend fun checkIsFavorite(query: String): Boolean {
        return dao.checkIsFavorite(query).isNotEmpty()
    }
}
