package com.muffar.remindtask.service

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.muffar.remindtask.RootActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmTaskReceiver : BroadcastReceiver() {

    @Inject
    lateinit var  notificationManager: TaskNotification

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.extras?.getString(EXTRA_TITLE)
        val id = intent.extras?.getInt(EXTRA_ID, 0)

        val resultIntent = Intent(context, RootActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(
            context,
            id ?: 0,
            resultIntent,
            PendingIntent.FLAG_MUTABLE
        )

        notificationManager.showNotification(id ?: 0, title ?: "", pendingIntent)
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TITLE = "EXTRA_TITLE"
    }
}