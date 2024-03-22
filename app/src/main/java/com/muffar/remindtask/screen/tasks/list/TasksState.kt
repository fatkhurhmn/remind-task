package com.muffar.remindtask.screen.tasks.list

import com.muffar.remindtask.domain.model.Task
import java.time.LocalDate

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val selectedDate : LocalDate = LocalDate.now()
)