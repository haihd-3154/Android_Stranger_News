package com.example.strangernews.data.source

import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse
import kotlinx.coroutines.flow.Flow

interface ArticleDataSource {

    interface Local {
        suspend fun getLocalArticles(): List<Article>
        suspend fun insertArticle(article: Article)
        suspend fun deleteArticle(article: Article)
        suspend fun checkIsFavorite(query: String): Boolean
    }

    interface Remote {
        suspend fun getRemoteArticles(query: QueryData): DataResponse
    }
}
