package com.muffar.remindtask.tasks.add

import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.Task

sealed class AddTaskEvent {
    data class OnTitleChange(val title: String) : AddTaskEvent()
    data class OnDescriptionChange(val description: String) : AddTaskEvent()
    data class OnDatePickerClick(val show: Boolean) : AddTaskEvent()
    data class OnTimePickerClick(val show: Boolean) : AddTaskEvent()
    data class OnDateSelected(val date: Long?) : AddTaskEvent()
    data class OnTimeSelected(val hour: Int, val minute: Int) : AddTaskEvent()
    data class OnPrioritySelect(val priority: PriorityType) : AddTaskEvent()
    data object OnSaveTaskClick : AddTaskEvent()
    data object OnEditTaskClick : AddTaskEvent()
    data class OnInitState(val task: Task?) : AddTaskEvent()
    data object OnRestoreTask : AddTaskEvent()
    data class OnShowDeleteDialog(val show: Boolean) : AddTaskEvent()
    data class OnShowDiscardDialog(val show: Boolean) : AddTaskEvent()
    data object OnDeleteTaskClick : AddTaskEvent()
}