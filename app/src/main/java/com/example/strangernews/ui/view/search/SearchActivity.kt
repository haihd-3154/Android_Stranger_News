package com.example.strangernews.ui.view.search

import android.database.Cursor
import android.database.MatrixCursor
import android.provider.BaseColumns
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.ActivitySearchBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.ui.viewmodel.SearchViewModel
import com.example.strangernews.utils.extension.showArticleBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

    private val searchVM: SearchViewModel by viewModel()
    private val savedVM: SavedViewModel by viewModel()
    private val articleAdapter: ArticleMainAdapter by lazy {
        ArticleMainAdapter(object : ItemClickListener<Article> {
            override fun onItemClick(item: Article?) {
            }

            override fun onItemLongClick(item: Article?): Boolean {
                item?.let {
                    showArticleBottomSheet(it, savedVM.listener)
                }
                return false
            }
        })
    }
    private var historyList = setOf<String>()
    private var historyAdapter: SimpleCursorAdapter? = null

    override fun initView() {
        binding?.apply {
            searchView.apply {
                isIconified = false
                clearFocus()
                requestFocusFromTouch()
                setOnQueryTextListener(this@SearchActivity)

            }
            searchList.apply {
                adapter = articleAdapter
            }
            btnBack.setOnClickListener {
                this@SearchActivity.finish()
            }
        }
    }

    override fun initData() {
        observerItem()
        SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            null,
            arrayOf(keywordName),
            intArrayOf(android.R.id.text1),
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        ).let {
            binding?.searchView?.apply {
                historyAdapter = it
                suggestionsAdapter = historyAdapter
                setOnSuggestionListener(this@SearchActivity)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchVM.search(it)
        }
        return true
    }

    override fun onQueryTextChange(newData: String?): Boolean {
        newData?.let {
            val cursor = MatrixCursor(arrayOf(BaseColumns._ID, keywordName))
            historyList.forEachIndexed { index, suggestion ->
                if (suggestion.contains(it, true)) {
                    cursor.addRow(arrayOf(index, suggestion))
                }
            }
            historyAdapter?.changeCursor(cursor)
        }
        return true
    }

    override fun onSuggestionSelect(p0: Int): Boolean {
        return false
    }

    override fun onSuggestionClick(position: Int): Boolean {
        binding?.searchView?.apply {
            val cursor = suggestionsAdapter.getItem(position) as Cursor
            val columnName = cursor.getColumnIndex(keywordName)
            val selection = cursor.getString(columnName)
            setQuery(selection, true)
        }
        return true
    }

    private fun observerItem() {
        searchVM.apply {
            listSearchArticle.observe(this@SearchActivity) {
                articleAdapter.submitList(it)
            }
            history.observe(this@SearchActivity) {
                it?.let { set ->
                    historyList = set
                }
            }
            isLoading.observe(this@SearchActivity) {
                if (it) {
                    startLoading()
                } else {
                    stopLoading()
                }
            }
        }
    }

    private fun startLoading() {
        binding?.shimmer?.apply {
            startShimmer()
            isVisible = true
        }
    }

    private fun stopLoading() {
        binding?.shimmer?.apply {
            stopShimmer()
            isVisible = false
        }
    }

    companion object {
        const val keywordName = "keywords"
    }
}
