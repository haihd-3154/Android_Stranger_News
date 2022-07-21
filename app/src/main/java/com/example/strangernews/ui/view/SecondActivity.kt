package com.example.strangernews.ui.view

import android.view.Menu
import android.view.MenuItem
import com.example.strangernews.R
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.databinding.ActivitySecondBinding
import com.example.strangernews.ui.view.saved.SavedFragment
import com.example.strangernews.ui.view.setting.SettingFragment
import com.example.strangernews.utils.extension.replaceFragment

class SecondActivity : BaseActivity<ActivitySecondBinding>(ActivitySecondBinding::inflate) {

    override fun initView() {
        val type = intent.getStringExtra(TYPE_EXTRA)
        supportFragmentManager.apply {
            when (type) {
                SETTING -> replaceFragment(R.id.navContainer, SettingFragment.newInstance())
                SAVED -> replaceFragment(R.id.navContainer, SavedFragment.newInstance())
                else -> replaceFragment(R.id.navContainer, SettingFragment.newInstance())
            }
        }
        binding?.apply {
            setSupportActionBar(toolbar)
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    override fun initData() {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_second_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding?.apply {
            when (item.itemId) {
                R.id.home -> {
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TYPE_EXTRA = "type"
        const val SETTING = "Setting"
        const val FILTER = "News"
        const val ALL_CATEGORY = "all category"
        const val CATEGORY = "Category"
        const val SAVED = "Saved"
        const val BACK_STACK = "my back stack"
    }
}
