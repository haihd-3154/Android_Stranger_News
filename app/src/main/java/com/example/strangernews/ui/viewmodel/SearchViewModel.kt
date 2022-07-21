package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import com.example.strangernews.data.repository.DataStoreRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    private val articleRepository: ArticleRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel() {

    private val _listSearchArticle: MutableLiveData<List<Article>> = MutableLiveData()
    val listSearchArticle: LiveData<List<Article>> = _listSearchArticle
    val history = dataStoreRepository.getSearchHistory().asLiveData()

    fun search(queryString: String) {
        launchTaskSync(
            {
                articleRepository.getRemoteArticles(
                    QueryData(
                        limit = searchLimit,
                        keywords = queryString
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
        safeCall{
            dataStoreRepository.addSearchHistory(keywords)
        }
    }

    companion object {
        const val searchLimit = 100
    }
}
