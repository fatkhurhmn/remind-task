package com.muffar.remindtask.screen.tasks.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.task.TaskUseCases
import com.muffar.remindtask.domain.usecase.user.UserUseCase
import com.muffar.remindtask.service.TaskNotification
import com.muffar.remindtask.service.scheduler.TaskScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val userUseCase: UserUseCase,
    private val taskScheduler: TaskScheduler,
    private val notificationManager: TaskNotification,
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var tasks = emptyList<Task>()

    init {
        initTasks()
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
            is TasksEvent.OnShowDialog -> _state.value =
                _state.value.copy(showDialog = event.show, selectedTask = event.task)

            is TasksEvent.OnShowSearchBar -> _state.value =
                _state.value.copy(showSearchBar = event.show)

            is TasksEvent.OnQueryChange -> {
                _state.value = _state.value.copy(searchQuery = event.query)
                filterTasks()
            }
        }
    }

    private fun initTasks() {
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

    private fun filterTasks() {
        val filteredTasks = taskUseCases.getTasks.filter(
            tasks = tasks,
            headerType = _state.value.headerType,
            selectedDate = _state.value.selectedDate,
            status = _state.value.status,
            timeType = _state.value.selectedTime,
            searchQuery = _state.value.searchQuery
        )
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun onHeaderTypeChanged(headerType: HeaderType) {
        viewModelScope.launch {
            val type =
                if (headerType == HeaderType.CALENDAR) HeaderType.CHIPS else HeaderType.CALENDAR
            userUseCase.saveHeaderType(type)
            filterTasks()
        }
    }

    private fun checkTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.checkTask(task)
        }

        if (task.status == StatusType.UNCOMPLETED) {
            taskScheduler.cancelTask(task.id ?: UUID.randomUUID())
            notificationManager.cancelNotification(task.id.hashCode())
        }
    }

    private fun deleteTask(id: UUID?) {
        viewModelScope.launch {
            taskUseCases.deleteTask(id)
        }
        taskScheduler.cancelTask(id ?: UUID.randomUUID())
        notificationManager.cancelNotification(id.hashCode())
    }
}