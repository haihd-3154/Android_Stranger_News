package com.example.strangernews.ui.view.saved

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.strangernews.R
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.FragmentSavedBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.extension.showArticleBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedFragment : BaseFragment<FragmentSavedBinding>(FragmentSavedBinding::inflate),
    AdapterView.OnItemSelectedListener {

    override val viewModel: SavedViewModel by viewModel()
    private val toolbarTitle = "Saved"
    private var currentSort = 0
    private val savedAdapterView: ArticleMainAdapter by lazy {
        ArticleMainAdapter(listener)
    }
    private val listener = object : ItemClickListener<Article> {
        override fun onItemClick(item: Article?) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra(Constants.ARTICLE_EXTRA, item)
                startActivity(this)
            }
        }

        override fun onItemLongClick(item: Article?): Boolean {
            item?.let {
                context?.showArticleBottomSheet(it, viewModel.listener)
            }
            return true
        }
    }

    override fun initView() {
        (activity as AppCompatActivity).supportActionBar?.title = toolbarTitle
        binding?.apply {
            context?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.sort_array,
                    android.R.layout.simple_spinner_item
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = this
                }
                spinner.onItemSelectedListener = this@SavedFragment
            }
            savedRecyclerView.adapter = savedAdapterView
        }
    }

    override fun initData() {
        observerItem()
    }

    override fun onItemSelected(p0: AdapterView<*>?, parent: View?, position: Int, id: Long) {
        currentSort = position
        savedAdapterView.apply {
            submitList(viewModel.savedArticles.value?.sortItems())
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    private fun observerItem() {
        viewModel.savedArticles.observe(viewLifecycleOwner) {
            it?.let {
                savedAdapterView.submitList(it.sortItems())
            }
        }
    }

    private fun List<Article>.sortItems(): List<Article> {
        return when (currentSort) {
            RECENT_POSITION -> this.sortedByDescending { it.publishedAt }
            LASTED_POSITION -> this.sortedBy { it.publishedAt }
            AUTHOR_POSITION -> this.sortedBy { it.author }
            TITLE_POSITION -> this.sortedBy { it.title }
            LANGUAGE_POSITION -> this.sortedBy { it.language }
            COUNTRY_POSITION -> this.sortedBy { it.country }
            SOURCE_POSITION -> this.sortedBy { it.source }
            else -> this
        }
    }

    companion object {
        const val RECENT_POSITION = 0
        const val LASTED_POSITION = 1
        const val AUTHOR_POSITION = 2
        const val TITLE_POSITION = 3
        const val LANGUAGE_POSITION = 4
        const val COUNTRY_POSITION = 5
        const val SOURCE_POSITION = 6

        @JvmStatic
        fun newInstance() = SavedFragment()
    }
}
