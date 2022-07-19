package com.example.strangernews.ui.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.strangernews.R
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.databinding.ActivityMainBinding
import com.example.strangernews.ui.view.search.SearchActivity
import com.example.strangernews.utils.constant.TypeOfSource
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    NavigationView.OnNavigationItemSelectedListener {

    override fun initView() {
        binding?.apply {
            includeToolbar.toolbar.apply {
                setSupportActionBar(this)
                setNavigationIcon(R.drawable.ic_menu)
            }
            supportActionBar?.setDisplayShowTitleEnabled(false)
            navigationView.setNavigationItemSelectedListener(this@MainActivity)
        }
    }
    override fun initData() {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home_toolbar, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding?.apply {
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    return true
                }
                R.id.nav_search -> {
                    Intent(this@MainActivity, SearchActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_search -> {
                Intent(this@MainActivity, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.nav_setting ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA,SecondActivity.SETTING)
                    startActivity(this)
                }
            }
            R.id.nav_saved ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA,SecondActivity.SAVED)
                    startActivity(this)
                }
            }
            R.id.nav_categories ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA, SecondActivity.ALL_CATEGORY)
                    putExtra(SecondActivity.LIST_DATA_TYPE_EXTRA, TypeOfSource.CATEGORY.name)
                    startActivity(this)
                }
            }
            R.id.nav_sources ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA, SecondActivity.ALL_CATEGORY)
                    putExtra(SecondActivity.LIST_DATA_TYPE_EXTRA, TypeOfSource.SOURCE.name)
                    startActivity(this)
                }
            }
            R.id.nav_language ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA, SecondActivity.ALL_CATEGORY)
                    putExtra(SecondActivity.LIST_DATA_TYPE_EXTRA, TypeOfSource.LANGUAGE.name)
                    startActivity(this)
                }
            }
            R.id.nav_countries ->{
                Intent(this, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.TYPE_EXTRA, SecondActivity.ALL_CATEGORY)
                    putExtra(SecondActivity.LIST_DATA_TYPE_EXTRA, TypeOfSource.COUNTRY.name)
                    startActivity(this)
                }
            }
        }
        binding?.apply {
            drawerLayout.closeDrawers()
        }
        return false
    }
}
