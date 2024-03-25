package com.muffar.remindtask.domain.usecase.note

import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val noteRepository: NoteRepository,
) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getAll()
    }
}
