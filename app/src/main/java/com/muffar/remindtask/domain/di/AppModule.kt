package com.muffar.remindtask.domain.di

import com.muffar.remindtask.domain.repository.TaskRepository
import com.muffar.remindtask.domain.repository.UserRepository
import com.muffar.remindtask.domain.usecase.task.GetTasks
import com.muffar.remindtask.domain.usecase.task.TaskUseCase
import com.muffar.remindtask.domain.usecase.user.GetHeaderType
import com.muffar.remindtask.domain.usecase.user.SaveHeaderType
import com.muffar.remindtask.domain.usecase.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTasksUseCase(
        taskRepository: TaskRepository,
    ): TaskUseCase = TaskUseCase(
        getTasks = GetTasks(taskRepository)
    )

    @Provides
    fun provideUsersUseCase(
        userRepository: UserRepository,
    ): UserUseCase = UserUseCase(
        getHeaderType = GetHeaderType(userRepository),
        saveHeaderType = SaveHeaderType(userRepository)
    )
}