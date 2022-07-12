package com.example.strangernews.ui.viewmodel

import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.repository.ArticleRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    val articleRepository : ArticleRepository
): BaseViewModel() {
    
}
