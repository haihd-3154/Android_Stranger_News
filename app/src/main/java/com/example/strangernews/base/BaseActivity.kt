package com.example.strangernews.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.strangernews.R
import com.example.strangernews.utils.extension.showSnackBar
import com.example.strangernews.utils.network.NetworkChangeCallback
import com.example.strangernews.utils.network.NetworkReceiver

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity(), NetworkChangeCallback {

    private var _binding: VB? = null
    private val networkListener = NetworkReceiver(this)
    protected val binding get() = _binding
    private var showNetworkStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding?.root)
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(networkListener, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkListener)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNetworkChange(isAvailable: Boolean) {
        if (isAvailable) {
            if (showNetworkStatus) {
                val notifyText = "Internet connected"
                binding?.root?.showSnackBar(notifyText, R.color.color_blue)
                showNetworkStatus = false
            }
        } else {
            if (!showNetworkStatus) {
                val notifyText = "Internet is not available!"
                binding?.root?.showSnackBar(notifyText, R.color.color_primary)
                showNetworkStatus = true
            }
        }
    }

    abstract fun initView()
    abstract fun initData()
}
