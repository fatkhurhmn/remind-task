package com.muffar.remindtask.screen.tasks.add

import com.muffar.remindtask.domain.model.PriorityType

data class AddTaskState(
    val title: String = "",
    val description : String = "",
    val isDatePickerOpen: Boolean = false,
    val isTimePickerOpen : Boolean = false,
    val selectedDate : Long? = null,
    val selectedHour: Int? = null,
    val selectedMinute: Int? = null,
    val priorityType: PriorityType = PriorityType.LOW
)
