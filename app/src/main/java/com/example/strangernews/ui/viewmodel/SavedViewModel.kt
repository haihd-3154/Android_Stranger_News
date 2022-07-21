package com.example.strangernews.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.repository.ArticleRepository
import com.example.strangernews.ui.callback.ArticleBottomSheetCallback
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.utils.constant.Constants
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SavedViewModel(
    private val articleRepository: ArticleRepository,
) : BaseViewModel() {

    init {
        getSavedArticles()
    }

    private val _savedArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val savedArticles: LiveData<List<Article>> = _savedArticles
    val listener = object : ArticleBottomSheetCallback {

        override fun checkIsFavorite(article: Article) {
            savedArticles.value?.forEach {
                if (it.compare(article)) {
                    article.isFavorite = true
                    return
                }
                article.isFavorite = false
            }
        }

        override fun favorite(article: Article) {
            if (article.isFavorite) {
                launchTaskSync({
                    articleRepository.deleteLocalArticle(article)
                }, {
                    article.isFavorite = false
                    getSavedArticles()
                })
            } else {
                launchTaskSync({
                    articleRepository.insertLocalArticle(article)
                }, {
                    article.isFavorite = true
                    getSavedArticles()
                })
            }
        }

        override fun share(article: Article, context: Context) {
            val intent = Intent.createChooser(
                Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, article.title)
                    putExtra(Intent.EXTRA_TEXT, article.url)
                    putExtra(Intent.EXTRA_TITLE, article.description)
                    type = "text/plain"
                }, null
            )
            startActivity(context, intent, null)
        }

        override fun open(article: Article, context: Context) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra(Constants.ARTICLE_EXTRA, article)
                startActivity(context, this, null)
            }
        }

        override fun browser(article: Article, context: Context) {
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(article.url)
                startActivity(context,this, null)
            }
        }

    }

    fun getSavedArticles() {
        launchTaskSync(
            onRequest = { articleRepository.getLocalArticles() },
            onSuccess = {
                it.forEach { ar ->
                    ar.isFavorite = true
                }
                _savedArticles.postValue(it)
            },
            onError = { errorResponse.postValue(it) }
        )
    }

    fun check(article: Article) {
        launchTaskSync({
            articleRepository.checkIsFavorite(article.title)
        }, {
            article.isFavorite = it
        })
    }
}
