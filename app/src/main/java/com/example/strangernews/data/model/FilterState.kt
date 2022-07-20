package com.example.strangernews.data.model

import com.example.strangernews.ui.viewmodel.FilterViewModel

data class FilterState(
    val categories: MutableSet<String> = mutableSetOf(),
    val sources: MutableSet<String> = mutableSetOf(),
    val languages: MutableSet<String> = mutableSetOf(),
    val countries: MutableSet<String> = mutableSetOf(),
    var limit: Int = 0,
    var date: String = "",
)
