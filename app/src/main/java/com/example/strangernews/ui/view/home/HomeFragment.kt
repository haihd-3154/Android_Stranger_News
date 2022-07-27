package com.example.strangernews.ui.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.FragmentHomeBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.adapter.HomeDataTypeAdapter
import com.example.strangernews.ui.adapter.HomeViewPagerAdapter
import com.example.strangernews.ui.adapter.ItemClickListener
import com.example.strangernews.ui.viewmodel.HomeViewModel
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.constant.SupportData
import com.example.strangernews.utils.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()
    private val savedVM: SavedViewModel by viewModel()
    private val sourceAdapter: HomeDataTypeAdapter by lazy {
        HomeDataTypeAdapter(
            object : ItemClickListener<DataType> {
                override fun onItemClick(item: DataType?) {
                }

                override fun onItemLongClick(item: DataType?): Boolean {
                    return true
                }
            }
        )
    }
    private val categoryAdapter: HomeDataTypeAdapter by lazy {
        HomeDataTypeAdapter(
            object : ItemClickListener<DataType> {
                override fun onItemClick(item: DataType?) {
                }

                override fun onItemLongClick(item: DataType?): Boolean {
                    return true
                }
            }
        )
    }

    private val breakingNewsAdapter: HomeViewPagerAdapter by lazy {
        HomeViewPagerAdapter(object : ItemClickListener<Article> {
            override fun onItemClick(item: Article?) {
            }

            override fun onItemLongClick(item: Article?): Boolean {
                return false
            }

        })
    }
    private val recentsAdapter: ArticleMainAdapter by lazy {
        ArticleMainAdapter(
            object : ItemClickListener<Article> {
                override fun onItemClick(item: Article?) {
                }

                override fun onItemLongClick(item: Article?): Boolean {
                    return false
                }
            }
        )
    }


    override fun initView() {
        binding?.apply {
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
                adapter = recentsAdapter
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
                recentsAdapter.submitList(it.take(Constants.RECENT_VALUE))
                breakingNewsAdapter.submitList(it.filter { ar ->
                    ar.image != null
                }.take(Constants.BREAKING_NEWS_VALUE))
                stopRefresh()
            }
            errorResponse.observe(viewLifecycleOwner){
                context.showToast(it?.message.toString())
            }
            stopRefresh()
        }
    }

    private fun stopRefresh() {
        binding?.apply {
            swipeRefresh.isRefreshing = false
        }
    }
}
