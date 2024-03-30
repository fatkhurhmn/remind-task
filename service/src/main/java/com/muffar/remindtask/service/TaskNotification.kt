package com.muffar.remindtask.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import javax.inject.Inject
import com.muffar.remindtask.resources.R

class TaskNotification @Inject constructor(private val context: Context) {

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createNotificationChannel()
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(notifyId: Int, content: String, pendingIntent: PendingIntent) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(NOTIFICATION_TITLE)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(notifyId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        return NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
    }

    fun cancelNotification(notifyId: Int) {
        notificationManager.cancel(notifyId)
    }

    companion object {
        const val CHANNEL_ID = "task_channel"
        const val CHANNEL_NAME = "Task Reminder"
        const val NOTIFICATION_TITLE = "Task Reminder"
    }
}