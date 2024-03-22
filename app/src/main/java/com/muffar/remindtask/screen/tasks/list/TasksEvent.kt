package com.muffar.remindtask.screen.tasks.list

import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.TimeType
import java.time.LocalDate

sealed class TasksEvent {
    data class OnDateSelected(val date: LocalDate) : TasksEvent()
    data class OnTimeSelected(val timeType: TimeType) : TasksEvent()
    data class OnHeaderTypeChanged(val headerType: HeaderType) : TasksEvent()
}