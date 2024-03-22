package com.muffar.remindtask.data.di

import com.muffar.remindtask.data.local.db.dao.TaskDao
import com.muffar.remindtask.data.repository.TaskRepositoryImpl
import com.muffar.remindtask.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)
}