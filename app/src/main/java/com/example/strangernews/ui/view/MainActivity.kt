package com.example.strangernews.ui.view

import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.strangernews.R
import com.example.strangernews.base.BaseActivity
import com.example.strangernews.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    NavigationView.OnNavigationItemSelectedListener {

    override fun initView() {
        binding?.apply {
            includeToolbar.toolbar.apply {
                setSupportActionBar(this)
                setNavigationIcon(R.drawable.ic_menu)
            }
            supportActionBar?.setDisplayShowTitleEnabled(false);
            navigationView.setNavigationItemSelectedListener(this@MainActivity)
        }
    }
    override fun initData() {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding?.apply {
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding?.apply {
            drawerLayout.closeDrawers()
        }
        return false
    }
}
