package com.example.strangernews.ui.callback

import com.example.strangernews.data.model.Article

interface ArticleBottomSheetCallback {
    fun favorite(article: Article)
    fun share(article: Article)
    fun open(article: Article)
    fun browser(article: Article)
}
