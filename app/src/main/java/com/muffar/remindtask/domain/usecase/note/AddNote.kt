package com.muffar.remindtask.domain.usecase.note

import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.save(note)
    }
}
