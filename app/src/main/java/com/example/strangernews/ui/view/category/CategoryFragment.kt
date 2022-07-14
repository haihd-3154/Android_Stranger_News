package com.example.strangernews.ui.view.category

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.databinding.FragmentCategoryBinding

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}
