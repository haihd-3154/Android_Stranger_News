package com.example.strangernews.ui.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.strangernews.ui.view.MainActivity
import com.example.strangernews.utils.constant.Constants

class SplashActivity : AppCompatActivity() {
    private var mainHandler : Handler? = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainHandler?.postDelayed({
            Intent(this, MainActivity::class.java).apply{
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
}
