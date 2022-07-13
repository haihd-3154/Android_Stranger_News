package com.example.strangernews.utils.extension

import com.example.strangernews.utils.constant.Constants.DATETIME_FORMAT_MMMM_YYYY_DD_H_MM_A
import com.example.strangernews.utils.constant.Constants.DATETIME_MAX_LENGTH
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.coverToDateTime(pattern: String = DATETIME_FORMAT_MMMM_YYYY_DD_H_MM_A): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val time = LocalDateTime.parse(this.subSequence(0, DATETIME_MAX_LENGTH))
        formatter.format(time)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}
