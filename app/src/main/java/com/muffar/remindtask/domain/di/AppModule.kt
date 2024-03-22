package com.muffar.remindtask.domain.di

import com.muffar.remindtask.domain.usecase.TaskUseCase
import com.muffar.remindtask.domain.usecase.TaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideTaskUseCase(taskUseCase: TaskUseCaseImpl): TaskUseCase
}