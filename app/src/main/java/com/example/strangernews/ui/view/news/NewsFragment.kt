package com.example.strangernews.ui.view.news

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import com.example.strangernews.R
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.Article
import com.example.strangernews.databinding.FragmentNewsBinding
import com.example.strangernews.ui.adapter.ArticleMainAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.ui.viewmodel.FilterViewModel
import com.example.strangernews.ui.viewmodel.SavedViewModel
import com.example.strangernews.utils.constant.Constants
import com.example.strangernews.utils.constant.SupportData
import com.example.strangernews.utils.extension.*
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {

    override val viewModel: FilterViewModel by viewModel()
    private val savedVM: SavedViewModel by viewModel()
    private val articleAdapter: ArticleMainAdapter by lazy {
        ArticleMainAdapter(articleListener)
    }
    private val articleListener = object : ItemClickListener<Article> {
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
    private val popupListener = PopupMenu.OnMenuItemClickListener { item ->
        when (item?.itemId) {
            R.id.dialog_category -> context?.showMultiChoiceDialog(
                CATEGORIES,
                allCategories,
                allCategoriesId.map { viewModel.getFilter().categories.contains(it) }
                    .toBooleanArray()
            ) { result ->
                updateFilter(result, FilterViewModel.CATEGORY_FILTER, allCategoriesId)
            }
            R.id.dialog_language -> context?.showMultiChoiceDialog(
                LANGUAGES,
                allLanguages,
                allLanguagesId.map {
                    viewModel.getFilter().languages.contains(it)
                }.toBooleanArray()
            ) { result ->
                updateFilter(result, FilterViewModel.LANGUAGE_FILTER, allLanguagesId)
            }
            R.id.dialog_source -> context?.showMultiChoiceDialog(
                SOURCES,
                allSources,
                allSourcesId.map {
                    viewModel.getFilter().sources.contains(it)
                }.toBooleanArray()
            ) { result ->
                updateFilter(result, FilterViewModel.SOURCE_FILTER, allSourcesId)
            }
            R.id.dialog_country -> context?.showMultiChoiceDialog(
                COUNTRIES,
                allCountries,
                allCountriesId.map {
                    viewModel.getFilter().countries.contains(it)
                }.toBooleanArray()
            ) { result ->
                updateFilter(result, FilterViewModel.COUNTRY_FILTER, allCountriesId)
            }
            R.id.dialog_date -> {
                context?.showDatePickerDialog {
                    viewModel.addFilter(FilterViewModel.DATE_FILTER, it)
                    updateFilterGroup()
                }
            }
            R.id.dialog_limit -> {
                context?.showSingleChoiceDialog(
                    LIMIT,
                    allLimit,
                    allLimit.indexOf(viewModel.getFilter().limit.toString())
                ) {
                    viewModel.addFilter(FilterViewModel.LIMIT_FILTER, allLimit.elementAt(it))
                    updateFilterGroup()
                }
            }
            R.id.clear -> {
                viewModel.clearFilter()
                updateFilterGroup()
            }
            else -> {}
        }
        true
    }

    override fun initView() {
        (activity as AppCompatActivity).supportActionBar?.title = TITLE
        binding?.apply {
            recyclerviewArticle.apply {
                adapter = articleAdapter
            }
            btnFilter.setOnClickListener {
                it?.showPopup(R.menu.menu_filter_picker, popupListener)
            }
        }
        updateFilterGroup()
    }

    override fun initData() {
        observerItem()
        viewModel.getArticlesByFilter()
    }

    private fun observerItem() {
        viewModel.apply {
            articlesResult.observe(viewLifecycleOwner) {
                articleAdapter.submitList(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                if (it == false) {
                    stopLoading()
                } else {
                    startLoading()
                }
            }
            errorResponse.observe(viewLifecycleOwner) {
                it?.apply {
                    context?.showToast(it.message.toString ())
                }
            }
        }
    }

    private fun startLoading() {
        binding?.apply {
            shimmer.apply {
                startShimmer()
                isVisible = true
            }
            recyclerviewArticle.isVisible = false
        }
    }

    private fun stopLoading() {
        binding?.apply {
            shimmer.apply {
                startShimmer()
                isVisible = false
            }
            recyclerviewArticle.isVisible = true
        }
    }

    private fun updateFilterGroup() {
        binding?.apply {
            if (viewModel.isNotEmptyFilter()) {
                groupFilter.apply {
                    removeAllViews()
                    viewModel.createListFilter().forEach {
                        addView(createChip(it))
                    }
                }
            } else {
                groupFilter.apply {
                    removeAllViews()
                    addView(createChip(EMPTY))
                }
            }
        }
        viewModel.getArticlesByFilter()
    }

    private fun createChip(id: String): Chip {
        return Chip(context).apply {
            text = "#$id"
            setTextColor(context.getResourceColor(R.color.color_white))
            chipBackgroundColor =
                ColorStateList.valueOf(context.getResourceColor(R.color.color_primary))
        }
    }

    private fun updateFilter(
        result: BooleanArray,
        type: String,
        source: Set<String>,
    ) {
        result.forEachIndexed { index, value ->
            if (value) {
                viewModel.addFilter(type, source.elementAt(index))
            } else {
                viewModel.removeFilter(type, source.elementAt(index))
            }
        }
        updateFilterGroup()
    }

    companion object {
        const val TITLE = "News"
        const val CATEGORIES = "Categories"
        const val COUNTRIES = "Countries"
        const val SOURCES = "Sources"
        const val LANGUAGES = "Languages"
        const val LIMIT = "Limit"
        const val EMPTY = "empty"
        val allCategories = SupportData.categories.map { it.name }.toSet()
        val allCountries = SupportData.countries.map { it.name }.toSet()
        val allSources = SupportData.sources.map { it.name }.toSet()
        val allLanguages = SupportData.languages.map { it.name }.toSet()
        val allCategoriesId = SupportData.categories.map { it.id }.toSet()
        val allCountriesId = SupportData.countries.map { it.id }.toSet()
        val allSourcesId = SupportData.sources.map { it.id }.toSet()
        val allLanguagesId = SupportData.languages.map { it.id }.toSet()
        val allLimit = setOf("10", "20", "30", "50", "80", "100")

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}
