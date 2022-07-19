package com.example.strangernews.ui.view.category

import android.content.Intent
import android.view.View
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.FragmentCategoryBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.adapter.CategoryViewPagerAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.callback.UpdatePositionCallback
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.ui.viewmodel.CategoryViewModel
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.extension.showArticleBottomSheet
import com.example.strangernews.utils.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment(
    val dataType: DataType
) : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    override val viewModel: CategoryViewModel by viewModel()
    private val savedVM: SavedViewModel by viewModel()
    private val articleAdapter: ArticleMainAdapter by lazy {
        ArticleMainAdapter(articleClickListener)
    }
    private val viewPagerAdapter: CategoryViewPagerAdapter by lazy {
        CategoryViewPagerAdapter(object : UpdatePositionCallback {
            override fun update(position: Int) {
                setPosition(position)
            }
        })
    }
    private val articleClickListener = object : ItemClickListener<Article> {
        override fun onItemClick(item: Article?) {
            item?.let {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(Constants.ARTICLE_EXTRA, item)
                    startActivity(this)
                }
            }
        }

        override fun onItemLongClick(item: Article?): Boolean {
            item?.let {
                context?.showArticleBottomSheet(it, savedVM.listener)
            }
            return false
        }
    }

    override fun initView() {
        binding?.apply {
            viewpagerDatatype.apply {
                adapter = viewPagerAdapter
                isUserInputEnabled = false
            }
            recyclerviewArticle.apply {
                adapter = articleAdapter
            }
        }
    }

    override fun initData() {
        viewModel.changeDataType(dataType.type)
        observerItem()
    }

    private fun observerItem() {
        viewModel.apply {
            listDataType.observe(viewLifecycleOwner) {
                viewPagerAdapter.submitList(it)
                setPosition(checkCurrentPosition())
            }
            articlesResult.observe(viewLifecycleOwner) {
                articleAdapter.submitList(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                when (it) {
                    false -> stopLoading()
                    else -> startLoading()
                }
            }
            errorResponse.observe(viewLifecycleOwner) {
                context.showToast(it?.message.toString())
            }
        }
    }

    private fun checkCurrentPosition(): Int {
        viewPagerAdapter.currentList.forEachIndexed { index, it ->
            it?.apply {
                if (it.compare(dataType)) return index
            }
        }
        return 0
    }

    private fun setPosition(position: Int) {
        binding?.viewpagerDatatype?.currentItem = position
        viewModel.getArticlesByDataType(viewPagerAdapter.currentList[position])
    }

    private fun startLoading() {
        binding?.apply {
            shimmer.apply {
                startShimmer()
                visibility = View.VISIBLE
            }
            recyclerviewArticle.visibility = View.GONE
        }
    }

    private fun stopLoading() {
        binding?.apply {
            shimmer.apply {
                stopShimmer()
                visibility = View.GONE
            }
            recyclerviewArticle.visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(dataType: DataType) = CategoryFragment(dataType)
    }
}
