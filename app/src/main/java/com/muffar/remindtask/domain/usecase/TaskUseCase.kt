package com.muffar.remindtask.domain.usecase

import com.muffar.remindtask.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TaskUseCase {
    suspend fun saveTask(task: Task)
    suspend fun deleteTask(id: UUID)
    fun getTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: UUID): Task?
}