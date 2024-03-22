package com.muffar.remindtask.screen.tasks.add

import com.muffar.remindtask.domain.model.PriorityType

sealed class AddTaskEvent {
    data class OnTitleChange(val title: String) : AddTaskEvent()
    data class OnDescriptionChange(val description: String) : AddTaskEvent()
    data class OnDatePickerClick(val show: Boolean) : AddTaskEvent()
    data class OnTimePickerClick(val show: Boolean) : AddTaskEvent()
    data class OnDateSelected(val date: Long?) : AddTaskEvent()
    data class OnTimeSelected(val hour: Int, val minute: Int) : AddTaskEvent()
    data class OnPrioritySelect(val priority: PriorityType) : AddTaskEvent()
    data object OnSaveClick : AddTaskEvent()
}