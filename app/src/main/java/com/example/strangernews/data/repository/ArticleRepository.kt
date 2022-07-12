package com.example.strangernews.data.repository

import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse
import com.example.strangernews.utils.DataResult

interface ArticleRepository {
    suspend fun getRemoteArticles(query: QueryData): DataResult<DataResponse>
    suspend fun insertLocalArticle(article: Article)
    suspend fun deleteLocalArticle(article: Article)
    suspend fun getLocalArticles() :DataResult<List<Article>>
}
