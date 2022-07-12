package com.example.strangernews.ui.view.search

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.databinding.FragmentSearchBinding
import com.example.strangernews.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
    }

    override fun initData() {
    }
}
