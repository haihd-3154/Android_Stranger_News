package com.example.strangernews.utils

import android.database.sqlite.SQLiteException
import com.example.strangernews.utils.constant.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

interface SafeCallData {
    suspend fun <T> safeCallData(dataCall: suspend () -> T): DataResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataResult.Success(dataCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        DataResult.Error(throwable)
                    }
                    is UnknownHostException -> {
                        DataResult.Error(throwable)
                    }
                    is SQLiteException ->{
                        DataResult.Error(throwable)
                    }
                    else -> {
                        DataResult.Error(Exception(Constants.ERROR_UNKNOWN))
                    }
                }
            }
        }
    }
}
