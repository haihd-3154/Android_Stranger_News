package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.DataType
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import com.example.strangernews.utils.constant.SupportData
import com.example.strangernews.utils.constant.TypeOfSource
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoryViewModel(
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    private val _listDataType: MutableLiveData<List<DataType>> = MutableLiveData()
    private val _articlesResult: MutableLiveData<List<Article>> = MutableLiveData()
    val listDataType: LiveData<List<DataType>> = _listDataType
    val articlesResult: LiveData<List<Article>> = _articlesResult

    fun changeDataType(dataType: String?) {
        when (dataType) {
            TypeOfSource.CATEGORY.name -> _listDataType.postValue(SupportData.categories)
            TypeOfSource.SOURCE.name -> _listDataType.postValue(SupportData.sources)
            TypeOfSource.LANGUAGE.name -> _listDataType.postValue(SupportData.languages)
            TypeOfSource.COUNTRY.name -> _listDataType.postValue(SupportData.countries)
            else -> _listDataType.postValue(SupportData.categories)
        }
    }

    fun getArticlesByDataType(dataType: DataType) {
        val queryData = QueryData().apply {
            languages = ""
            when (dataType.type) {
                TypeOfSource.CATEGORY.name -> categories = dataType.id
                TypeOfSource.SOURCE.name -> sources = dataType.id
                TypeOfSource.LANGUAGE.name -> languages = dataType.id
                TypeOfSource.COUNTRY.name -> countries = dataType.id
            }
        }
        launchTaskSync(
            {
                articleRepository.getRemoteArticles(queryData)
            }, {
                _articlesResult.postValue(it.data)
            }
        )
    }
}
