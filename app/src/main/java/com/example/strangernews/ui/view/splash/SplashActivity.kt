package com.example.strangernews.ui.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.strangernews.data.source.local.datastore.DataStoreManager
import com.example.strangernews.ui.view.MainActivity
import com.example.strangernews.utils.constant.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val datastore: DataStoreManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            checkAppMode()
            switchToMain()
        }
    }

    private suspend fun checkAppMode() {
        when (datastore.appMode.first()) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private suspend fun switchToMain() {
        delay(Constants.SPLASH_TIME)
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }
}
