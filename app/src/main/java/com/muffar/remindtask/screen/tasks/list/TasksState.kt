package com.muffar.remindtask.screen.tasks.list

import com.muffar.remindtask.model.HeaderType
import com.muffar.remindtask.model.Task
import com.muffar.remindtask.model.StatusType
import com.muffar.remindtask.model.TimeType
import java.time.LocalDate

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val status : StatusType? = null,
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTime: TimeType = TimeType.TODAY,
    val headerType: HeaderType = HeaderType.CALENDAR,
    val showDialog : Boolean = false,
    val selectedTask: Task? = null,
    val showSearchBar : Boolean = false,
    val searchQuery : String = ""
)