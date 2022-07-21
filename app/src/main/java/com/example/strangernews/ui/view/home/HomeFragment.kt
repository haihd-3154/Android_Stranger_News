package com.example.strangernews.ui.view.home

import android.content.Intent
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.FragmentHomeBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.adapter.HomeDataTypeAdapter
import com.example.strangernews.ui.adapter.HomeViewPagerAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.ui.viewmodel.HomeViewModel
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.constant.Constants.ARTICLE_EXTRA
import com.example.strangernews.utils.constant.SupportData
import com.example.strangernews.utils.extension.showArticleBottomSheet
import com.example.strangernews.utils.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()
    private val savedVM: SavedViewModel by viewModel()
    private var isFirstTime = true
    private val sourceAdapter: HomeDataTypeAdapter by lazy {
        HomeDataTypeAdapter(dataTypeClickListener)
    }
    private val categoryAdapter: HomeDataTypeAdapter by lazy {
        HomeDataTypeAdapter(dataTypeClickListener)
    }

    private val breakingNewsAdapter: HomeViewPagerAdapter by lazy {
        HomeViewPagerAdapter(articleClickListener)
    }
    private val recentAdapter: ArticleMainAdapter by lazy {
        ArticleMainAdapter(articleClickListener)
    }

    private val articleClickListener = object : ItemClickListener<Article> {
        override fun onItemClick(item: Article?) {
            item?.let {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(ARTICLE_EXTRA, item)
                    startActivity(this)
                }
            }
        }

        override fun onItemLongClick(item: Article?): Boolean {
            item?.let {
                context?.showArticleBottomSheet(it,savedVM.listener)
            }
            return false
        }
    }

    private val dataTypeClickListener = object : ItemClickListener<DataType> {
        override fun onItemClick(item: DataType?) {
        }

        override fun onItemLongClick(item: DataType?): Boolean {
            return true
        }
    }

    override fun initView() {
        binding?.apply {
            startShimmerLoading()
            swipeRefresh.apply {
                setOnRefreshListener {
                    viewModel.getListArticles()
                }
            }
            recyclerviewCategories.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerviewSources.apply {
                adapter = sourceAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            viewpagerBreakingNews.apply {
                adapter = breakingNewsAdapter
            }
            recyclerviewRecent.apply {
                adapter = recentAdapter
            }
        }
    }

    override fun initData() {
        observerItem()
        categoryAdapter.submitList(SupportData.categories)
        sourceAdapter.submitList(SupportData.sources)
    }

    private fun observerItem() {
        viewModel.apply {
            listArticles.observe(viewLifecycleOwner) {
                recentAdapter.submitList(it.take(Constants.RECENT_VALUE))
                breakingNewsAdapter.submitList(it.filter { ar ->
                    ar.image != null
                }.take(Constants.BREAKING_NEWS_VALUE))
                stopRefresh()
                if (isFirstTime && it.isNotEmpty()) {
                    stopShimmerLoading()
                }
            }
            errorResponse.observe(viewLifecycleOwner) {
                context.showToast(it?.message.toString())
                stopRefresh()
            }
        }
    }

    private fun startShimmerLoading() {
        binding?.apply {
            homeShimmer.startShimmer()
            layoutHome.isVisible = false
        }
    }


    private fun stopShimmerLoading() {
        binding?.apply {
            homeShimmer.apply {
                stopShimmer()
                isVisible = false
            }
            layoutHome.isVisible = true
        }
    }

    private fun stopRefresh() {
        binding?.apply {
            swipeRefresh.isRefreshing = false
        }
    }
}
