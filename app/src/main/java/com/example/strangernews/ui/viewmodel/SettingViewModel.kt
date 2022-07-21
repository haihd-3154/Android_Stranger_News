package com.example.strangernews.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.strangernews.base.BaseViewModel
import com.example.strangernews.data.repository.DataStoreRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingViewModel(private val dataStoreRepository: DataStoreRepository) : BaseViewModel() {

    val isDailyNews = dataStoreRepository.getDailyNew()
    val dailyTime = dataStoreRepository.getDailyTime()

    fun updateAppMode(value: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.updateAppMode(value)
        }
    }

    fun updateDailNew(isOn: Boolean, time: String) {
        viewModelScope.launch {
            dataStoreRepository.updateDailNew(isOn, time)
        }
    }
}
