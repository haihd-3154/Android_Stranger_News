package com.example.strangernews.ui.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.source.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingViewModel(private val datastore: DataStoreManager) : BaseViewModel() {

    val isDailyNews = datastore.dailyNew
    val dailyTime = datastore.dailyTime

    fun updateAppMode(value: Boolean) {
        viewModelScope.launch {
            datastore.updateAppMode(value)
        }
    }

    fun updateDailNew(isOn: Boolean, time: String, setTime: Boolean = true) {
        viewModelScope.launch {
            datastore.updateDailyNew(isOn, time, setTime)
        }
    }
}