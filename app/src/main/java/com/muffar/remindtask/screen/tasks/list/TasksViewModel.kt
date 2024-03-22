package com.muffar.remindtask.screen.tasks.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.domain.usecase.TaskUseCase
import com.muffar.remindtask.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var tasks = emptyList<Task>()

    init {
        viewModelScope.launch {
            taskUseCase.getTasks().collect { listTasks ->
                tasks = listTasks
                userUseCase.getHeaderType().let {
                    _state.value = _state.value.copy(headerType = it)
                }
                filterTasks()
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

            is TasksEvent.OnHeaderTypeChanged -> {
                onHeaderTypeChanged(event.headerType)
            }
        }
    }

    private fun filterTasks() {
        if (_state.value.headerType == HeaderType.CALENDAR) {
            filterTasksByDate()
        } else {
            filterTasksByTime()
        }
        filterTasksByStatus()
    }

    private fun filterTasksByDate() {
        val filteredTasks = tasks.filter {
            val instant = Instant.ofEpochMilli(it.deadline)
            val zoneId = ZoneId.systemDefault()
            val localDate = instant.atZone(zoneId).toLocalDate()
            localDate == _state.value.selectedDate
        }
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun filterTasksByTime() {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate =
            Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault()).toLocalDate()

        val filteredTasks = tasks.filter {
            val inputDate =
                Instant.ofEpochMilli(it.deadline).atZone(ZoneId.systemDefault()).toLocalDate()

            when (_state.value.selectedTime) {
                TimeType.TODAY -> inputDate == currentDate
                TimeType.PAST -> inputDate < currentDate
                TimeType.SOON -> inputDate.isAfter(currentDate)
                TimeType.ALL -> true
            }
        }
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun filterTasksByStatus() {
        val status = _state.value.status
        val filteredTasks = _state.value.tasks.filter {
            when (status) {
                null -> true
                else -> it.status == status
            }
        }
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
}