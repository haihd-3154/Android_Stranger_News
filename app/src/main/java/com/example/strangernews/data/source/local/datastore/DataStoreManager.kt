package com.example.strangernews.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.strangernews.utils.extension.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class DataStoreManager(context: Context) {

    private val datastore = context.dataStore
    val searchHistory = datastore.data.map { preferences ->
        preferences[SEARCH_HISTORY]
    }
    val appMode = datastore.data.map { preferences ->
        preferences[APP_MODE]
    }
    val dailyNew = datastore.data.map { preferences ->
        preferences[DAILY_NEW]
    }
    val dailyTime = datastore.data.map { preferences ->
        preferences[DAILY_TIME]
    }

    suspend fun addSearchHistory(searchHistory: String) {
        launchDataStore {
            datastore.edit { preferences ->
                val listKeywords = mutableSetOf<String>()
                preferences[SEARCH_HISTORY]?.let { list ->
                    listKeywords.addAll(list)
                }
                listKeywords.add(searchHistory)
                preferences[SEARCH_HISTORY] = listKeywords
            }
        }
    }

    suspend fun updateAppMode(value: Boolean) {
        launchDataStore {
            datastore.edit { preferences ->
                preferences[APP_MODE] = value
            }
        }
    }

    suspend fun updateDailyNew(isOn: Boolean, time: String) {
        launchDataStore {
            datastore.edit { preferences ->
                preferences[DAILY_NEW] = isOn
                if (isOn) preferences[DAILY_TIME] = time
            }
        }
    }

    private suspend fun launchDataStore(call: suspend () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            call()
        }
    }


    companion object {
        val SEARCH_HISTORY = stringSetPreferencesKey("app_key_search_history")
        val APP_MODE = booleanPreferencesKey("app_dark_mode")
        val DAILY_NEW = booleanPreferencesKey("app_daily_news")
        val DAILY_TIME = stringPreferencesKey("app_daily_time")
    }
}
