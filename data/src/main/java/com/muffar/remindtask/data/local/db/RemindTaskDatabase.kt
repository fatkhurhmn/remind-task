package com.muffar.remindtask.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muffar.remindtask.data.local.db.dao.NoteDao
import com.muffar.remindtask.data.local.db.dao.TaskDao
import com.muffar.remindtask.data.local.db.entity.NoteEntity
import com.muffar.remindtask.data.local.db.entity.TaskEntity

@Database(entities = [TaskEntity::class, NoteEntity::class], version = 3)
@TypeConverters(RoomTypeConverters::class)
abstract class RemindTaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val noteDao: NoteDao

    companion object {
        fun create(applicationContext: Context): RemindTaskDatabase {
            return Room
                .databaseBuilder(
                    applicationContext,
                    RemindTaskDatabase::class.java,
                    DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
        }

        private const val DATABASE_NAME = "remind_task.db"
    }
}