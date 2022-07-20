package com.example.strangernews.ui.callback

import android.content.Context
import com.example.strangernews.data.model.Article

interface ArticleBottomSheetCallback {
    fun checkIsFavorite(article: Article): Boolean
    fun favorite(article: Article, context: Context? = null)
    fun share(article: Article, context: Context)
    fun open(article: Article, context: Context)
    fun browser(article: Article, context: Context)
}
