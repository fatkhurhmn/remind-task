package com.muffar.remindtask.service.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.service.AlarmTaskReceiver
import java.util.UUID
import javax.inject.Inject

class AlarmTaskScheduler @Inject constructor(
    private val context: Context,
) : TaskScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setTask(task: Task) {
        val intent = Intent(context, AlarmTaskReceiver::class.java).apply {
            putExtra(AlarmTaskReceiver.EXTRA_ID, task.id.hashCode())
            putExtra(AlarmTaskReceiver.EXTRA_TITLE, task.title)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            task.deadline,
            pendingIntent
        )
    }

    override fun cancelTask(id: UUID) {
        val intent = Intent(context, AlarmTaskReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id.hashCode(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.cancel(pendingIntent)
    }
}