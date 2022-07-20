package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.FilterState
import com.example.strangernews.data.model.QueryData
import com.example.strangernews.data.repository.ArticleRepository
import com.example.strangernews.utils.extension.toQueryString
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FilterViewModel(
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    private var filter = FilterState(
        limit = DEFAULT_LIMIT_FILTER
    )
    private val _articlesResult = MutableLiveData<List<Article>>()
    val articlesResult: LiveData<List<Article>> = _articlesResult

    fun getFilter(): FilterState = filter

    fun getArticlesByFilter() {
        launchTaskSync(
            {
                articleRepository.getRemoteArticles(createQueryData())
            },
            {
                _articlesResult.postValue(it.data)
            }
        )
    }

    private fun createQueryData() = QueryData().apply {
        categories = filter.categories.toQueryString()
        sources = filter.sources.toQueryString()
        languages = filter.languages.toQueryString()
        countries = filter.countries.toQueryString()
        date = filter.date
        limit = filter.limit
    }

    fun addFilter(type: String, data: String) {
        when (type) {
            LANGUAGE_FILTER -> filter.languages.add(data)
            COUNTRY_FILTER -> filter.countries.add(data)
            CATEGORY_FILTER -> filter.categories.add(data)
            SOURCE_FILTER -> filter.sources.add(data)
            DATE_FILTER -> filter.date = data
            LIMIT_FILTER -> filter.limit = data.toIntOrNull() ?: DEFAULT_LIMIT_FILTER
        }
    }

    fun removeFilter(type: String, data: String) {
        when (type) {
            LANGUAGE_FILTER -> filter.languages.remove(data)
            COUNTRY_FILTER -> filter.countries.remove(data)
            CATEGORY_FILTER -> filter.categories.remove(data)
            SOURCE_FILTER -> filter.sources.remove(data)
            DATE_FILTER -> filter.date = ""
            LIMIT_FILTER -> filter.limit = DEFAULT_LIMIT_FILTER
        }
    }

    fun isNotEmptyFilter(): Boolean {
        return !(filter.sources.isEmpty()
                &&filter.languages.isEmpty()
                && filter.countries.isEmpty()
                && filter.categories.isEmpty()
                && filter.date.isEmpty()
                && filter.limit == DEFAULT_LIMIT_FILTER
                )
    }

    fun createListFilter(): List<String>{
        val filters = mutableListOf<String>()
        filters.addAll(filter.categories)
        filters.addAll(filter.sources)
        filters.addAll(filter.languages)
        filters.addAll(filter.countries)
        filters.add(filter.limit.toString())
        if (filter.date.isNotEmpty()) filters.add(filter.date)
        return filters
    }
    fun clearFilter(){
        filter = FilterState(
            limit = DEFAULT_LIMIT_FILTER
        )
    }

    companion object {
        const val LANGUAGE_FILTER = "languages"
        const val COUNTRY_FILTER = "countries"
        const val DATE_FILTER = "dates"
        const val SOURCE_FILTER = "sources"
        const val CATEGORY_FILTER = "categories"
        const val LIMIT_FILTER = "limits"
        const val DEFAULT_LIMIT_FILTER = 10
    }
}
