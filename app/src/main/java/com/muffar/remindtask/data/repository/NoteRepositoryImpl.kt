package com.muffar.remindtask.data.repository

import com.muffar.remindtask.data.local.db.dao.NoteDao
import com.muffar.remindtask.data.repository.mapper.NoteMapper.toDomain
import com.muffar.remindtask.data.repository.mapper.NoteMapper.toEntity
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun save(note: Note) {
        noteDao.save(note.toEntity())
    }

    override suspend fun deleteById(id: UUID) {
        noteDao.deleteById(id)
    }

    override suspend fun getById(id: UUID): Note? {
        return noteDao.getById(id)?.toDomain()
    }
}