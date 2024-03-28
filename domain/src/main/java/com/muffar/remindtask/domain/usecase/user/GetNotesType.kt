package com.muffar.remindtask.domain.usecase.user

import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetNotesType(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<NotesType> =
        userRepository.getNotesType()
}
