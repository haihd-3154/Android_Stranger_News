package com.example.strangernews.data.repository

import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse
import com.example.strangernews.utils.DataResult

interface ArticleRepository {
    suspend fun getRemoteArticles(query: QueryData): DataResult<DataResponse>
    suspend fun insertLocalArticle(article: Article): DataResult<Unit>
    suspend fun deleteLocalArticle(article: Article): DataResult<Unit>
    suspend fun getLocalArticles(): DataResult<List<Article>>
    suspend fun checkIsFavorite(query: String): DataResult<Boolean>
}
