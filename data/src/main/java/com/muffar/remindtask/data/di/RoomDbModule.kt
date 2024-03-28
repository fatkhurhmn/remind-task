package com.muffar.remindtask.data.di

import android.content.Context
import com.muffar.remindtask.data.local.db.RemindTaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {

    @Provides
    @Singleton
    fun provideRemindTaskDatabase(
        @ApplicationContext context: Context,
    ): RemindTaskDatabase = RemindTaskDatabase.create(context)

    @Provides
    fun provideTaskDao(remindTaskDatabase: RemindTaskDatabase) = remindTaskDatabase.taskDao

    @Provides
    fun provideNoteDao(remindTaskDatabase: RemindTaskDatabase) = remindTaskDatabase.noteDao
}