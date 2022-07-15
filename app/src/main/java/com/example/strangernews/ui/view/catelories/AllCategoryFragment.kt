package com.example.strangernews.ui.view.catelories

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.databinding.FragmentAllCategoryBinding

class AllCategoryFragment : BaseFragment<FragmentAllCategoryBinding>(FragmentAllCategoryBinding::inflate) {

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllCategoryFragment()
    }
}
