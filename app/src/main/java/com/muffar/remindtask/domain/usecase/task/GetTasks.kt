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
        return tasks.filter {
            if (headerType == HeaderType.CALENDAR) {
                filterTasksByDate(it, selectedDate)
            } else {
                filterTasksByTime(it, timeType)
            }
        }.filter {
            filterTasksByStatus(it, status)
        }.filter {
            filterTasksByQuery(it, searchQuery)
        }
    }

    private fun filterTasksByDate(task: Task, selectedDate: LocalDate): Boolean {
        val instant = Instant.ofEpochMilli(task.deadline)
        val zoneId = ZoneId.systemDefault()
        val localDate = instant.atZone(zoneId).toLocalDate()
        return localDate == selectedDate
    }

    private fun filterTasksByTime(task: Task, selectedTime: TimeType): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        val currentDate =
            Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault()).toLocalDate()

        val inputDate =
            Instant.ofEpochMilli(task.deadline).atZone(ZoneId.systemDefault()).toLocalDate()

        return when (selectedTime) {
            TimeType.TODAY -> inputDate == currentDate
            TimeType.EXPIRED -> inputDate < currentDate
            TimeType.UPCOMING -> inputDate.isAfter(currentDate)
            TimeType.ALL -> true
        }
    }

    private fun filterTasksByStatus(task: Task, status: StatusType?): Boolean {
        return when (status) {
            null -> true
            else -> task.status == status
        }
    }

    private fun filterTasksByQuery(task: Task, searchQuery: String): Boolean {
        return task.title.contains(searchQuery, ignoreCase = true)
    }
}