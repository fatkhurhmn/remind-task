package com.muffar.remindtask.screen.tasks.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.InvalidTaskException
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.task.TaskUseCases
import com.muffar.remindtask.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AddTaskState())
    val state: State<AddTaskState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.OnTitleChange -> {
                _state.value = state.value.copy(title = event.title)
            }

            is AddTaskEvent.OnDescriptionChange -> {
                _state.value = state.value.copy(description = event.description)
            }

            is AddTaskEvent.OnDatePickerClick -> {
                _state.value = state.value.copy(isDatePickerOpen = event.show)
            }

            is AddTaskEvent.OnTimePickerClick -> {
                _state.value = state.value.copy(isTimePickerOpen = event.show)
            }

            is AddTaskEvent.OnDateSelected -> {
                _state.value = state.value.copy(selectedDate = event.date)
            }

            is AddTaskEvent.OnTimeSelected -> {
                _state.value =
                    state.value.copy(selectedHour = event.hour, selectedMinute = event.minute)
            }

            is AddTaskEvent.OnPrioritySelect -> {
                _state.value = state.value.copy(priorityType = event.priority)
            }

            is AddTaskEvent.OnSaveClick -> saveTask()
        }
    }

    private fun saveTask() {
        viewModelScope.launch {
            try {
                val deadline = Converter.combineTimeMillis(
                    state.value.selectedDate,
                    state.value.selectedHour,
                    state.value.selectedMinute
                )

                val task = Task(
                    title = state.value.title,
                    description = state.value.description,
                    deadline = deadline ?: 0,
                    priority = state.value.priorityType,
                    status = StatusType.UNCOMPLETED
                )
                taskUseCases.addTask(task)
                _eventFlow.emit(UiEvent.SaveTask)
            } catch (e: InvalidTaskException) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message.toString()))
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveTask : UiEvent()
    }
}