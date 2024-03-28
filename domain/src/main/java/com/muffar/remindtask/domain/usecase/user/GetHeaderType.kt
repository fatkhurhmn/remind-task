package com.muffar.remindtask.domain.usecase.user

import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetHeaderType(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<HeaderType> {
        return userRepository.getHeaderType()
    }
}