package com.example.strangernews.data.repository

import com.example.strangernews.data.source.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class DataStoreRepositoryImpl(private val datastore: DataStoreManager) : DataStoreRepository {

    override fun getSearchHistory(): Flow<Set<String>?> = datastore.searchHistory

    override suspend fun addSearchHistory(keyword: String) {
        datastore.addSearchHistory(keyword)
    }

    override fun getAppMode(): Flow<Boolean?> = datastore.appMode

    override suspend fun updateAppMode(value: Boolean) {
        datastore.updateAppMode(value)
    }

    override fun getDailyNew(): Flow<Boolean?> = datastore.dailyNew

    override fun getDailyTime(): Flow<String?> = datastore.dailyTime

    override suspend fun updateDailNew(isDailyNew: Boolean, time: String) {
        datastore.updateDailyNew(isDailyNew, time)
    }
}
