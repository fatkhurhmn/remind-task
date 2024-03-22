package com.muffar.remindtask.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.muffar.remindtask.data.local.db.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TaskDao {

    @Upsert
    suspend fun save(taskEntity: TaskEntity)

    @Query("SELECT * FROM task ORDER BY deadline ASC")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getById(id: UUID): TaskEntity?

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteById(id: UUID)
}