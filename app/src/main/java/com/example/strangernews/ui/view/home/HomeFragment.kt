package com.example.strangernews.ui.view.home

import com.example.strangernews.base.BaseFragment
import com.example.strangernews.databinding.FragmentHomeBinding
import com.example.strangernews.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
    }

    override fun initData() {
    }
}
