package com.example.strangernews.data.source.remote.api

import com.example.strangernews.data.model.data_response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news")
    suspend fun getArticles(
        @Query("access_key") access_key: String,
        @Query("keywords") keywords: String,
        @Query("categories") categories: String,
        @Query("sources") sources: String,
        @Query("languages") languages: String,
        @Query("countries") countries: String,
        @Query("date") date: String,
    ): DataResponse
}
