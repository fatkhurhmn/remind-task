package com.muffar.remindtask.screen.tasks.list

import java.time.LocalDate

sealed class TasksEvent {
    data class SelectDate(val date: LocalDate) : TasksEvent()
}