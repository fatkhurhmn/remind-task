package com.muffar.remindtask.service

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmTaskReceiver : BroadcastReceiver() {

    @Inject
    lateinit var  notificationManager: TaskNotification

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.extras?.getString(EXTRA_TITLE)
        val id = intent.extras?.getInt(EXTRA_ID, 0)

        val pendingIntent = PendingIntent.getActivity(
            context,
            id ?: 0,
            Intent(),
            PendingIntent.FLAG_IMMUTABLE
        )

        notificationManager.showNotification(id ?: 0, title ?: "", pendingIntent)
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TITLE = "EXTRA_TITLE"
    }
}