package com.example.strangernews.data.source.remote

import com.example.strangernews.data.source.ArticleDataSource
import com.example.strangernews.data.source.remote.api.ApiService
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse
import com.example.strangernews.utils.constant.Constants

class ArticleRemoteImpl(private val api: ApiService): ArticleDataSource.Remote {

    override suspend fun getRemoteArticles(query: QueryData): DataResponse {
        return api.getArticles(
            access_key = Constants.ACCESS_KEY,
            keywords = query.keywords,
            categories = query.categories,
            sources = query.sources,
            languages = query.languages,
            countries = query.countries,
            date = query.date
        )
    }
}
