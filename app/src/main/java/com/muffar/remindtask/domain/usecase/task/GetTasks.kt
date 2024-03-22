package com.muffar.remindtask.domain.usecase.task

import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class GetTasks(
    private val taskRepository: TaskRepository,
) {

    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAll()
    }

    fun filter(
        tasks: List<Task>,
        headerType: HeaderType,
        selectedDate: LocalDate,
        status: StatusType?,
        timeType: TimeType,
        searchQuery: String,
    ): List<Task> {
        val filteredTasks = arrayListOf<Task>()
        if (headerType == HeaderType.CALENDAR) {
            filteredTasks.addAll(filterTasksByDate(tasks, selectedDate))
        } else {
            filteredTasks.addAll(filterTasksByTime(tasks, timeType))
        }
        return filterTasksByStatus(filteredTasks, status).filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }
    }

    private fun filterTasksByDate(tasks: List<Task>, selectedDate: LocalDate): List<Task> {
        val filteredTasks = tasks.filter {
            val instant = Instant.ofEpochMilli(it.deadline)
            val zoneId = ZoneId.systemDefault()
            val localDate = instant.atZone(zoneId).toLocalDate()
            localDate == selectedDate
        }
        return filteredTasks
    }

    private fun filterTasksByTime(tasks: List<Task>, selectedTime: TimeType): List<Task> {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate =
            Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault()).toLocalDate()

        val filteredTasks = tasks.filter {
            val inputDate =
                Instant.ofEpochMilli(it.deadline).atZone(ZoneId.systemDefault()).toLocalDate()

            when (selectedTime) {
                TimeType.TODAY -> inputDate == currentDate
                TimeType.PAST -> inputDate < currentDate
                TimeType.SOON -> inputDate.isAfter(currentDate)
                TimeType.ALL -> true
            }
        }
        return filteredTasks
    }

    private fun filterTasksByStatus(tasks: List<Task>, status: StatusType?): List<Task> {
        val filteredTasks = tasks.filter {
            when (status) {
                null -> true
                else -> it.status == status
            }
        }
        return filteredTasks
    }

    private fun filterTasksBySearch(tasks: List<Task>, searchQuery: String): List<Task> {
        val filteredTasks = tasks.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        }
        return filteredTasks
    }
}