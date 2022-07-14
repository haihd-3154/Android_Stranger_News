package com.example.strangernews.ui.view.saved

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.databinding.FragmentSavedBinding

class SavedFragment : BaseFragment<FragmentSavedBinding>(FragmentSavedBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = SavedFragment()
    }
}
