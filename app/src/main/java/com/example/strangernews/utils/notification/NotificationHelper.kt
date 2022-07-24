package com.example.strangernews.utils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.strangernews.R
import com.example.strangernews.data.model.Article
import com.example.strangernews.ui.view.detail.DetailActivity
import com.example.strangernews.utils.constant.Constants

class NotificationHelper(val context: Context) {
    companion object{
        private const val channelName = "Strange News"
        private const val channelDescription = "Daily News"
        private const val channelId = "1923"
        const val notificationId = 1342
        const val imageHeight = 128
    }

    private fun createNotificationManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createArticleNotification(article: Article){
        createNotificationManager()
        val repeatingIntent = Intent(context, DetailActivity::class.java).apply {
            putExtra(Constants.ARTICLE_EXTRA, article)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            repeatingIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_newspaper)
                .setLargeIcon(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.drawable.img_default
                        ), imageHeight, imageHeight, false
                    )
                )
                .setContentTitle(article.title)
                .setContentText(article.description)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, builder.build())
    }
}
