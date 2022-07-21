package com.example.strangernews.data.repository

import com.example.strangernews.base.BaseRepository
import com.example.strangernews.data.source.ArticleDataSource
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.model.data_response.DataResponse
import com.example.strangernews.utils.DataResult
import org.koin.core.annotation.Single

@Single(binds = [ArticleRepository::class])
class ArticleRepositoryImpl(
    private val local: ArticleDataSource.Local,
    private val remote: ArticleDataSource.Remote
) : BaseRepository(), ArticleRepository {

    override suspend fun getRemoteArticles(query: QueryData): DataResult<DataResponse> {
        return safeCallData { remote.getRemoteArticles(query) }
    }

    override suspend fun insertLocalArticle(article: Article): DataResult<Unit> {
        return safeCallData { local.insertArticle(article) }
    }

    override suspend fun deleteLocalArticle(article: Article): DataResult<Unit> {
        return safeCallData { local.deleteArticle(article) }
    }

    override suspend fun getLocalArticles(): DataResult<List<Article>> {
        return safeCallData { local.getLocalArticles() }
    }

    override suspend fun checkIsFavorite(query: String): DataResult<Boolean> {
        return safeCallData { local.checkIsFavorite(query) }
    }
}
