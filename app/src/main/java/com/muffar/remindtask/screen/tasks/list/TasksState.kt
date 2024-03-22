package com.muffar.remindtask.screen.tasks.list

import com.muffar.remindtask.domain.model.Task

data class TasksState(
    val tasks: List<Task> = emptyList()
)