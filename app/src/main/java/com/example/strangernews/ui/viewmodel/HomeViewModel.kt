package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val articleRepository: ArticleRepository,
) : BaseViewModel() {

    init{
        getListArticles()
    }

    private val limitQuery = 50
    private val _listArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val listArticles: LiveData<List<Article>> = _listArticles

    fun getListArticles() {
        launchTaskSync(
            onRequest = { articleRepository.getRemoteArticles(QueryData(limit = limitQuery)) },
            onSuccess = { _listArticles.postValue(it.data) },
            onError = { errorResponse.postValue(it) }
        )
    }
}
