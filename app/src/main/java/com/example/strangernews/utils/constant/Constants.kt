package com.example.strangernews.utils.constant

import com.example.strangernews.BuildConfig

object Constants {
    const val SPLASH_TIME = 1500L
    const val ACCESS_KEY = BuildConfig.API_ACCESS_KEY
    const val DATASTORE_NAME= "settings"
    const val ERROR_UNKNOWN = "UnKnown"
    const val ERROR_OCCUR = "Some thing went wrong"
    const val BASE_URL = "http://api.mediastack.com/v1/"
    const val BREAKING_NEWS_VALUE = 10
    const val RECENT_VALUE = 10
    const val DATETIME_FORMAT_MMMM_YYYY_DD_H_MM_A = "MMMM yyyy,dd h:mm a"
    const val DATE_FORMAT_MMMM_YYYY_DD = "MMMM yyyy,dd"
    const val DATETIME_MAX_LENGTH = 19
    const val DEFAULT_LANGUAGE = "en"
}
