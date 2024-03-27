package com.muffar.remindtask.domain.usecase.note

import com.muffar.remindtask.domain.model.InvalidNoteException
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository,
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank() && note.description.isBlank()) {
            throw InvalidNoteException("Title and description cannot be empty. Please fill one of them.")
        }
        noteRepository.save(note)
    }
}
