package com.muffar.remindtask.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.muffar.remindtask.data.local.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDao {

    @Upsert
    suspend fun save(noteEntity: NoteEntity)

    @Query("SELECT * FROM note ORDER BY createdAt DESC")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getById(id: UUID): NoteEntity?

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteById(id: UUID)
}