package com.muffar.remindtask.screen.tasks.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.task.TaskUseCases
import com.muffar.remindtask.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var tasks = emptyList<Task>()

    init {
        viewModelScope.launch {
            taskUseCases.getTasks().collectLatest { tasksList ->
                tasks = tasksList
                userUseCase.getHeaderType().collectLatest {
                    _state.value = _state.value.copy(headerType = it)
                    filterTasks()
                }
            }
        }
    }

    fun onEvent(event: TasksEvent) {
        when (event) {

            is TasksEvent.OnStatusSelected -> {
                _state.value = _state.value.copy(status = event.status)
                filterTasks()
            }

            is TasksEvent.OnDateSelected -> {
                _state.value = _state.value.copy(selectedDate = event.date)
                filterTasks()
            }

            is TasksEvent.OnTimeSelected -> {
                _state.value = _state.value.copy(selectedTime = event.timeType)
                filterTasks()
            }

            is TasksEvent.OnHeaderTypeChanged -> onHeaderTypeChanged(event.headerType)

            is TasksEvent.OnTaskClick -> checkTask(event.task)

            is TasksEvent.OnTaskDelete -> deleteTask(event.id)
        }
    }

    private fun filterTasks() {
        val filteredTasks = taskUseCases.getTasks.filter(
            tasks = tasks,
            headerType = _state.value.headerType,
            selectedDate = _state.value.selectedDate,
            status = _state.value.status,
            timeType = _state.value.selectedTime
        )
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun onHeaderTypeChanged(headerType: HeaderType) {
        viewModelScope.launch {
            val type =
                if (headerType == HeaderType.CALENDAR) HeaderType.CHIPS else HeaderType.CALENDAR
            userUseCase.saveHeaderType(type)
            _state.value = _state.value.copy(headerType = type)
            filterTasks()
        }
    }

    private fun checkTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.checkTask(task)
        }
    }

    private fun deleteTask(id: UUID?) {
        viewModelScope.launch {
            taskUseCases.deleteTask(id)
        }
    }
}