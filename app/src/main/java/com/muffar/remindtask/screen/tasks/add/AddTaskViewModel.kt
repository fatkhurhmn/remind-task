package com.muffar.remindtask.screen.tasks.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.InvalidTaskException
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

            is AddTaskEvent.OnInitState -> initState(event.task)
        }
    }

    private fun saveTask() {
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
                _eventFlow.emit(UiEvent.SaveTask(task))
            } catch (e: InvalidTaskException) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message.toString()))
            }
        }
    }

    private fun initState(task: Task) {
        _state.value = state.value.copy(
            id = task.id,
            title = task.title,
            description = task.description ?: "",
            selectedDate = task.deadline,
            selectedHour = Converter.formattedDate(task.deadline, "HH").toInt(),
            selectedMinute = Converter.formattedDate(task.deadline, "mm").toInt(),
            priorityType = task.priority
        )
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class SaveTask(val task: Task) : UiEvent()
    }
}