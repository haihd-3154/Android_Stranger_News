package com.example.strangernews.ui.view.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.strangernews.R
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.ActivityDetailBinding
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants.ARTICLE_EXTRA
import com.example.strangernews.utils.extension.showArticleBottomSheet
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private var mArticle: Article? = null
    private var mWebView: WebView? = null
    private val savedVM: SavedViewModel by viewModel()
    private var mWebViewClient = object : WebViewClient() {
        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
            stopLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mArticle = intent.getParcelableExtra(ARTICLE_EXTRA) as Article?
        super.onCreate(savedInstanceState)
    }


    override fun initView() {
        binding?.apply {
            setSupportActionBar(toolbar)
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
            webView.apply {
                mWebView = this
                webViewClient = mWebViewClient
                mArticle?.let {
                    loadUrl(it.url)
                }
                settings.apply {
                    javaScriptEnabled = true
                    setSupportZoom(true)
                }
            }
        }
    }

    override fun initData() {
        supportActionBar?.apply {
            title = TOOLBAR_TITLE
        }
        checkIsFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding?.apply {
            when (item.itemId) {
                R.id.more -> mArticle?.let {
                    showArticleBottomSheet(ar = it, savedVM.listener, open = false)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        mWebView?.apply {
            if (canGoBack()) {
                goBack()
            } else {
                super.onBackPressed();
            }
        }
    }

    private fun stopLoading() {
        binding?.apply {
            indicator.isVisible = false
            webView.isVisible = true
        }
    }

    private fun checkIsFavorite() {
        lifecycleScope.launch {
            mArticle?.let {
                savedVM.check(it)
            }
        }
    }

    companion object {
        private const val TOOLBAR_TITLE = "Article"
    }
}
