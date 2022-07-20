package com.example.strangernews.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
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

    private suspend fun launchDataStore(call: suspend () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            call()
        }
    }

    companion object {
        val SEARCH_HISTORY = stringSetPreferencesKey("app_key_search_history")
    }
}
