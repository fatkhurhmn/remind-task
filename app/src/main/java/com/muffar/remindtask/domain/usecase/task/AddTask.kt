package com.muffar.remindtask.domain.usecase.task

import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.repository.TaskRepository

class AddTask(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.save(task)
    }
}