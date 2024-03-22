package com.muffar.remindtask.domain.usecase

import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository,
) : TaskUseCase {

    override suspend fun saveTask(task: Task) {
        taskRepository.save(task)
    }

    override suspend fun deleteTask(id: UUID) {
        taskRepository.deleteById(id)
    }

    override fun getTasks(): Flow<List<Task>> {
        return taskRepository.getAll()
    }

    override suspend fun getTaskById(id: UUID): Task? {
        return taskRepository.getById(id)
    }
}