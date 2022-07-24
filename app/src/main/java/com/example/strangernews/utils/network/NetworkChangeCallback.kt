package com.example.strangernews.utils.network

interface NetworkChangeCallback {
    fun onNetworkChange(isAvailable: Boolean)
}