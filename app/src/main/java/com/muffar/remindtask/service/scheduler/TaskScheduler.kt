package com.muffar.remindtask.service.scheduler

import com.muffar.remindtask.model.Task
import java.util.UUID

interface TaskScheduler {
    fun setTask(task: Task)
    fun cancelTask(id: UUID)
}