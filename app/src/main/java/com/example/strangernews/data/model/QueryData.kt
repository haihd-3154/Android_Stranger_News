package com.example.strangernews.data.model

data class QueryData(
    var limit: Int = 10,
    var keywords: String = "",
    var categories: String = "",
    var sources: String = "",
    var languages: String = "",
    var countries: String = "",
    var date: String = ""
)
