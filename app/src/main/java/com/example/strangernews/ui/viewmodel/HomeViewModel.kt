package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    init {
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

    fun updateSaved(savedList: List<Article>) {
        viewModelScope.launch(Dispatchers.IO) {
            val tempt = listArticles.value?.map { tempArticle ->
                savedList.forEach { savedArticle ->
                    if (tempArticle.compare(savedArticle)) tempArticle.isFavorite = true
                }
                tempArticle
            }
            _listArticles.postValue(tempt)
        }
    }
}
