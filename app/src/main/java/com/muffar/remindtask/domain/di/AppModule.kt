package com.muffar.remindtask.domain.di

import com.muffar.remindtask.domain.usecase.TaskUseCase
import com.muffar.remindtask.domain.usecase.TaskUseCaseImpl
import com.muffar.remindtask.domain.usecase.UserUseCase
import com.muffar.remindtask.domain.usecase.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideTaskUseCase(taskUseCase: TaskUseCaseImpl): TaskUseCase

    @Binds
    abstract fun provideUserUseCase(userUseCase: UserUseCaseImpl): UserUseCase
}