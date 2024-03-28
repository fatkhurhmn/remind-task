package com.muffar.remindtask.domain.usecase.note

import com.muffar.remindtask.domain.repository.NoteRepository
import java.util.UUID

class DeleteNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: UUID?) {
        id?.let {
            noteRepository.deleteById(id)
        }
    }
}
