package com.example.strangernews.data.model.data_response

import com.example.strangernews.data.model.Article
import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("pagination")
    var pagination: Pagination? = null,
    @SerializedName("data")
    var data: List<Article>? = null
)
