package com.muffar.remindtask.domain.usecase.user

import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.domain.repository.UserRepository

class SaveNotesType(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(notesType: NotesType) =
        userRepository.saveNotesType(notesType)
}
