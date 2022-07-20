package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import com.example.strangernews.data.source.local.datastore.DataStoreManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val articleRepository: ArticleRepository,
    private val datastore: DataStoreManager
) : BaseViewModel() {

    private var searchLimit = 100
    private val _listSearchArticle: MutableLiveData<List<Article>> = MutableLiveData()
    private val datastoreException = CoroutineExceptionHandler { _, exception ->
        errorResponse.postValue(exception)
    }
    val listSearchArticle: LiveData<List<Article>> = _listSearchArticle
    val history = datastore.searchHistory.asLiveData()

    fun search(queryString: String) {
        launchTaskSync(
            {
                articleRepository.getRemoteArticles(
                    QueryData(
                        limit = searchLimit,
                        keywords = queryString,
                        languages = ""
                    )
                )
            },
            {
                _listSearchArticle.postValue(it.data)
                addHistory(queryString)
            },
            { errorResponse.postValue(it) }
        )
    }

    private fun addHistory(keywords: String) {
        viewModelScope.launch(datastoreException) {
            datastore.addSearchHistory(keywords)
        }
    }
}
