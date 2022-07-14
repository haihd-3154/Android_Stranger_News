package com.example.strangernews.data.model

import com.example.strangernews.utils.constant.Constants

data class QueryData(
    var limit: Int = 10,
    var keywords: String = "",
    var categories: String = "",
    var sources: String = "",
    var languages: String = Constants.DEFAULT_LANGUAGE,
    var countries: String = "",
    var date: String = ""
)
