package com.example.strangernews.ui.view.catelories

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.strangernews.R
import com.example.strangernews.base.BaseFragment
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.FragmentAllCategoryBinding
import com.example.strangernews.ui.adapter.CategoryDataTypeAdapter
import com.example.strangernews.ui.callback.ItemClickListener
import com.example.strangernews.ui.view.SecondActivity
import com.example.strangernews.ui.view.category.CategoryFragment
import com.example.strangernews.ui.viewmodel.CategoryViewModel
import com.example.strangernews.utils.constant.SupportData
import com.example.strangernews.utils.constant.TypeOfSource
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCategoryFragment(
    val type: String
) : BaseFragment<FragmentAllCategoryBinding>(FragmentAllCategoryBinding::inflate) {

    override val viewModel: CategoryViewModel by viewModel()
    private val spanCount = 3
    private val dataTypeAdapter: CategoryDataTypeAdapter by lazy {
        CategoryDataTypeAdapter(dataTypeClickListener)
    }
    private val dataTypeClickListener = object : ItemClickListener<DataType> {
        override fun onItemClick(item: DataType?) {
            item?.let {
                activity?.apply {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.navContainer, CategoryFragment.newInstance(it))
                        .addToBackStack(SecondActivity.BACK_STACK)
                        .commit()
                }
            }
        }

        override fun onItemLongClick(item: DataType?): Boolean {
            return true
        }
    }

    override fun initView() {
        (activity as AppCompatActivity).supportActionBar?.title = type
        binding?.apply {
            recyclerviewAllCategory.apply {
                adapter = dataTypeAdapter
                layoutManager = GridLayoutManager(context, spanCount)
            }
        }
    }

    override fun initData() {
        dataTypeAdapter.submitList(
            when (type) {
                TypeOfSource.CATEGORY.name -> SupportData.categories
                TypeOfSource.SOURCE.name -> SupportData.sources
                TypeOfSource.COUNTRY.name -> SupportData.countries
                TypeOfSource.LANGUAGE.name -> SupportData.languages
                else -> SupportData.categories
            }
        )
    }


    companion object {
        @JvmStatic
        fun newInstance(type: String) = AllCategoryFragment(type)
    }
}
