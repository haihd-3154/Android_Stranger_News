package com.example.strangernews.data.source

import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse

interface ArticleDataSource {

    interface Local{
        suspend fun getLocalArticles(): List<Article>
        suspend fun insertArticle(article: Article)
        suspend fun deleteArticle(article: Article)
    }

    interface Remote{
        suspend fun getRemoteArticles(query: QueryData) : DataResponse
    }
}
