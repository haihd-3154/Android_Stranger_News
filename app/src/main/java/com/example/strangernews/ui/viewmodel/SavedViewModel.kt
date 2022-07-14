package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.repository.ArticleRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SavedViewModel(
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    private val _savedArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val savedArticles: LiveData<List<Article>> = _savedArticles

    fun getSavedArticles() {
        launchTaskSync(
            onRequest = { articleRepository.getLocalArticles() },
            onSuccess = { _savedArticles.postValue(it) },
            onError = { errorResponse.postValue(it) }
        )
    }
}
