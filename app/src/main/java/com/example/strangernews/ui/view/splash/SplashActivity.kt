package com.example.strangernews.ui.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.strangernews.data.source.local.datastore.DataStoreManager
import com.example.strangernews.ui.view.MainActivity
import com.example.strangernews.utils.constant.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private var mainHandler: Handler? = Handler(Looper.getMainLooper())
    private val datastore : DataStoreManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkAppMode()
        mainHandler?.postDelayed({
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, Constants.SPLASH_TIME)
    }

    override fun onDestroy() {
        mainHandler?.removeCallbacksAndMessages(null)
        mainHandler = null
        super.onDestroy()
    }

    private fun checkAppMode(){
        runBlocking{
            when(datastore.appMode.first()){
                true ->  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
