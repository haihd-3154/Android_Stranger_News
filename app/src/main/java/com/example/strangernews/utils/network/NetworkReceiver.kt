package com.example.strangernews.utils.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkReceiver(private val listener: NetworkChangeCallback) : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        listener.onNetworkChange(NetworkManager.isAvailable(context))
    }
}

