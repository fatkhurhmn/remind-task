package com.muffar.remindtask.tasks.add

import com.muffar.remindtask.domain.model.PriorityType
import java.util.UUID

data class AddTaskState(
    val id: UUID? = null,
    val title: String = "",
    val description: String = "",
    val isDatePickerOpen: Boolean = false,
    val isTimePickerOpen: Boolean = false,
    val selectedDate: Long? = null,
    val selectedHour: Int? = null,
    val selectedMinute: Int? = null,
    val priorityType: PriorityType = PriorityType.LOW,
    val isAddMode: Boolean = id == null,
    val isReadOnly: Boolean = true,
    val showDeleteDialog: Boolean = false,
    val showDiscardDialog: Boolean = false,
)
