package com.example.strangernews.ui.view.filter

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.databinding.FragmentFiterBinding

class FilterFragment : BaseFragment<FragmentFiterBinding>(FragmentFiterBinding::inflate) {

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
