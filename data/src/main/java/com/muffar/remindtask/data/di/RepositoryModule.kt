package com.muffar.remindtask.data.di

import com.muffar.remindtask.data.local.db.dao.NoteDao
import com.muffar.remindtask.data.local.db.dao.TaskDao
import com.muffar.remindtask.data.local.preferences.UserPreferences
import com.muffar.remindtask.data.repository.NoteRepositoryImpl
import com.muffar.remindtask.data.repository.TaskRepositoryImpl
import com.muffar.remindtask.data.repository.UserRepositoryImpl
import com.muffar.remindtask.domain.repository.NoteRepository
import com.muffar.remindtask.domain.repository.TaskRepository
import com.muffar.remindtask.domain.repository.UserRepository
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

    @Provides
    @Singleton
    fun provideUserRepository(userPreferences: UserPreferences): UserRepository = UserRepositoryImpl(userPreferences)

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)
}