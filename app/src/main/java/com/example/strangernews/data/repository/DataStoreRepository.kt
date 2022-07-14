package com.example.strangernews.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getSearchHistory(): Flow<Set<String>?>
    suspend fun addSearchHistory(keyword: String)
    fun getAppMode(): Flow<Boolean?>
    suspend fun updateAppMode(value: Boolean)
    fun getDailyNew(): Flow<Boolean?>
    fun getDailyTime(): Flow<String?>
    suspend fun updateDailNew(isDailyNew: Boolean, time: String)
}
