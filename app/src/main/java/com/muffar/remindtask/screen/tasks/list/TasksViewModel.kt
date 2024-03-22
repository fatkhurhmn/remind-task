package com.muffar.remindtask.screen.tasks.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.usecase.TaskUseCase
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
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var tasks = emptyList<Task>()

    init {
        viewModelScope.launch {
            taskUseCase.getTasks().collect {
                tasks = it
                filterTasksByDate(state.value.selectedDate)
            }
        }
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.SelectDate -> {
                _state.value = _state.value.copy(selectedDate = event.date)
                filterTasksByDate(state.value.selectedDate)
            }
        }
    }

    private fun filterTasksByDate(date: LocalDate){
        val filteredTasks = tasks.filter {
            val instant = Instant.ofEpochMilli(it.deadline)
            val zoneId = ZoneId.systemDefault()
            val localDate = instant.atZone(zoneId).toLocalDate()
            localDate == date
        }
        _state.value = _state.value.copy(tasks = filteredTasks)
    }
}