package com.muffar.remindtask.tasks.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.InvalidTaskException
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.task.TaskUseCases
import com.muffar.remindtask.service.scheduler.TaskScheduler
import com.muffar.remindtask.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val taskScheduler: TaskScheduler,
) : ViewModel() {

    private val _state = mutableStateOf(AddTaskState())
    val state: State<AddTaskState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTask: Task? = null

    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.OnTitleChange -> onTitleChange(event.title)
            is AddTaskEvent.OnDescriptionChange -> onDescriptionChange(event.description)
            is AddTaskEvent.OnDatePickerClick -> onDatePickerClick(event.show)
            is AddTaskEvent.OnTimePickerClick -> onTimePickerClick(event.show)
            is AddTaskEvent.OnDateSelected -> onDateSelected(event.date)
            is AddTaskEvent.OnTimeSelected -> onTimeSelected(event.hour, event.minute)
            is AddTaskEvent.OnPrioritySelect -> onPriorityChange(event.priority)
            is AddTaskEvent.OnSaveTaskClick -> onSaveTaskClick()
            is AddTaskEvent.OnEditTaskClick -> onEditTaskClick()
            is AddTaskEvent.OnInitState -> initState(event.task)
            is AddTaskEvent.OnRestoreTask -> onRestoreTask()
            is AddTaskEvent.OnShowDeleteDialog -> onDeleteShowDialog(event.show)
            is AddTaskEvent.OnShowDiscardDialog -> onShowDiscardDialog(event.show)
            is AddTaskEvent.OnDeleteTaskClick -> onDeleteTaskClick()
        }
    }

    private fun onTitleChange(title: String) {
        _state.value = state.value.copy(title = title)
    }

    private fun onDescriptionChange(description: String) {
        _state.value = state.value.copy(description = description)
    }

    private fun onDatePickerClick(show: Boolean) {
        _state.value = state.value.copy(isDatePickerOpen = show)
    }

    private fun onTimePickerClick(show: Boolean) {
        _state.value = state.value.copy(isTimePickerOpen = show)
    }

    private fun onDateSelected(date: Long?) {
        _state.value = state.value.copy(selectedDate = date)
    }

    private fun onTimeSelected(hour: Int, minute: Int) {
        _state.value = state.value.copy(selectedHour = hour, selectedMinute = minute)
    }

    private fun onPriorityChange(priority: PriorityType) {
        _state.value = state.value.copy(priorityType = priority)
    }

    private fun onSaveTaskClick() {
        viewModelScope.launch {
            try {
                val id = UUID.randomUUID()
                val deadline = Converter.combineTimeMillis(
                    state.value.selectedDate,
                    state.value.selectedHour,
                    state.value.selectedMinute
                )

                val task = Task(
                    id = state.value.id ?: id,
                    title = state.value.title,
                    description = state.value.description,
                    deadline = deadline ?: 0,
                    priority = state.value.priorityType,
                    status = StatusType.UNCOMPLETED
                )
                taskUseCases.addTask(task)
                taskScheduler.setTask(task)
                if (_state.value.isAddMode) {
                    _eventFlow.emit(UiEvent.SaveTask)
                } else {
                    _state.value = state.value.copy(isReadOnly = true)
                }
            } catch (e: InvalidTaskException) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message.toString()))
            }
        }
    }

    private fun onEditTaskClick() {
        _state.value = state.value.copy(isReadOnly = false)
    }

    private fun initState(task: Task?) {
        currentTask = task
        if (task != null) {
            _state.value = state.value.copy(
                id = task.id,
                title = task.title,
                description = task.description ?: "",
                selectedDate = task.deadline,
                selectedHour = Converter.formattedDate(task.deadline, "HH").toInt(),
                selectedMinute = Converter.formattedDate(task.deadline, "mm").toInt(),
                priorityType = task.priority,
                isAddMode = false,
                isReadOnly = true
            )
        } else {
            _state.value = state.value.copy(
                isAddMode = true,
                isReadOnly = false
            )
        }
    }

    private fun onDeleteTaskClick() {
        _state.value.id?.let {
            viewModelScope.launch {
                taskUseCases.deleteTask(it)
                _eventFlow.emit(UiEvent.DeleteTask)
            }
        }
    }

    private fun onRestoreTask() {
        currentTask?.let { task ->
            _state.value = AddTaskState(
                id = task.id,
                title = task.title,
                description = task.description ?: "",
                selectedDate = task.deadline,
                selectedHour = Converter.formattedDate(task.deadline, "HH").toInt(),
                selectedMinute = Converter.formattedDate(task.deadline, "mm").toInt(),
                priorityType = task.priority,
                isReadOnly = true
            )
        }
    }

    private fun onDeleteShowDialog(show: Boolean) {
        _state.value = state.value.copy(showDeleteDialog = show)
    }

    private fun onShowDiscardDialog(show: Boolean) {
        _state.value = state.value.copy(showDiscardDialog = show)
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveTask : UiEvent()
        data object DeleteTask : UiEvent()
    }
}