package com.muffar.remindtask.domain.repository

import com.muffar.remindtask.domain.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface NoteRepository {
    fun getAll(): Flow<List<Note>>
    suspend fun save(note: Note)
    suspend fun deleteById(id: UUID)
    suspend fun getById(id: UUID): Note?
}