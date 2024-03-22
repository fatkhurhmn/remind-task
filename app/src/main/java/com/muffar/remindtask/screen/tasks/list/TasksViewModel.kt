package com.muffar.remindtask.screen.tasks.list

import android.os.Build
import androidx.annotation.RequiresApi
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
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
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
            userUseCase.getHeaderType().let {
                _state.value = _state.value.copy(headerType = it)
            }

            taskUseCase.getTasks().collect {
                tasks = it
                filterTasksByDate(state.value.selectedDate)
            }
        }
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.OnDateSelected -> {
                _state.value = _state.value.copy(selectedDate = event.date)
                filterTasksByDate(state.value.selectedDate)
            }

            is TasksEvent.OnTimeSelected -> {
                _state.value = _state.value.copy(selectedTime = event.timeType)
                filterTasksByTime(state.value.selectedTime)
            }

            is TasksEvent.OnHeaderTypeChanged -> {
                viewModelScope.launch {
                    val headerType =
                        if (event.headerType == HeaderType.CALENDAR) HeaderType.CHIPS else HeaderType.CALENDAR
                    _state.value = _state.value.copy(headerType = headerType)
                    userUseCase.saveHeaderType(headerType)
                }
            }
        }
    }

    private fun filterTasksByDate(date: LocalDate) {
        val filteredTasks = tasks.filter {
            val instant = Instant.ofEpochMilli(it.deadline)
            val zoneId = ZoneId.systemDefault()
            val localDate = instant.atZone(zoneId).toLocalDate()
            localDate == date
        }
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun filterTasksByTime(timeType: TimeType) {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate = Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault()).toLocalDate()

        val filteredTasks = tasks.filter {
            val inputDate = Instant.ofEpochMilli(it.deadline).atZone(ZoneId.systemDefault()).toLocalDate()

            when (timeType) {
                TimeType.TODAY -> inputDate == currentDate
                TimeType.PAST -> inputDate < currentDate
                TimeType.SOON -> inputDate.isAfter(currentDate)
                TimeType.ALL -> true
            }
        }
        _state.value = _state.value.copy(tasks = filteredTasks)
    }
}