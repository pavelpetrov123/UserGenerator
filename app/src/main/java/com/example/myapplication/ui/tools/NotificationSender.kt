package com.example.myapplication.ui.tools

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

const val APP_NOTIFICATION_ID = 5584320
const val APP_NOTIFICATION_CHANNEL = "com.example.myapplication.APP_NOTIFICATION"

class NotificationSender(val context: Context?,
                         private val notificationManager: NotificationManager?) {

    fun cancelNotification(id: Int) {
        notificationManager?.cancel(id)
    }

    fun cancelAllNotification() {
        notificationManager?.cancelAll()
    }

    private fun showNotification(notification: Notification, id: Int) =
        notificationManager?.notify(id, notification)

    fun getAppNotification(title: String, text: String, context: Context?, initChannel: Boolean): Notification? {
        val ctx = context ?: return null
        if (initChannel) {
            buildNotificationChannel(ctx, APP_NOTIFICATION_CHANNEL)
        }

        val builder = NotificationCompat.Builder(ctx, APP_NOTIFICATION_CHANNEL)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(text)

        return builder.build()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun buildNotificationChannel(context: Context?, channelId: String): NotificationManager {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(channelId,
                context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT)
            )
            notificationManager
        } else {
            notificationManager
        }
    }
}