package com.example.strangernews.utils.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.strangernews.data.model.Article

class NotificationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        NotificationHelper(appContext).createArticleNotification(
            Article(
                title = inputData.getString(TITLE_PARAM) ?: "",
                url = inputData.getString(URL_PARAM) ?: "",
                description = inputData.getString(DESCRIPTION_PARAM) ?: ""
            )
        )
        return Result.success()
    }

    companion object {
        const val TITLE_PARAM = "title"
        const val URL_PARAM = "url"
        const val DESCRIPTION_PARAM = "description"
    }
}