package com.example.strangernews.ui.view

import android.view.Menu
import android.view.MenuItem
import com.example.strangernews.R
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.data.model.DataType
import com.example.strangernews.databinding.ActivitySecondBinding
import com.example.strangernews.ui.view.category.CategoryFragment
import com.example.strangernews.ui.view.catelories.AllCategoryFragment
import com.example.strangernews.ui.view.news.NewsFragment
import com.example.strangernews.ui.view.saved.SavedFragment
import com.example.strangernews.ui.view.setting.SettingFragment

class SecondActivity : BaseActivity<ActivitySecondBinding>(ActivitySecondBinding::inflate) {

    override fun initView() {
        val type = intent.getStringExtra(TYPE_EXTRA)
        supportFragmentManager.beginTransaction().apply{
            when(type){
                SETTING -> replace(R.id.navContainer,SettingFragment.newInstance())
                SAVED -> replace(R.id.navContainer, SavedFragment.newInstance())
                NEWS -> replace(R.id.navContainer, NewsFragment.newInstance())
                CATEGORY -> {
                    intent.getParcelableExtra<DataType>(DATA_TYPE_EXTRA)?.let {
                        replace(R.id.navContainer, CategoryFragment.newInstance(it))
                    }
                }
                ALL_CATEGORY -> {
                    intent.getStringExtra(LIST_DATA_TYPE_EXTRA)?.let {
                        replace(R.id.navContainer, AllCategoryFragment.newInstance(it))
                    }
                }
                else -> replace(R.id.navContainer,SettingFragment.newInstance())
            }
        }.commit()
        binding?.apply{
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
        const val DATA_TYPE_EXTRA = "datatype"
        const val LIST_DATA_TYPE_EXTRA = "list_datatype"
        const val SETTING = "Setting"
        const val NEWS = "News"
        const val ALL_CATEGORY = "all category"
        const val CATEGORY = "Category"
        const val SAVED = "Saved"
        const val BACK_STACK = "my back stack"
    }
}