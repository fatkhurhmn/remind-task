package com.muffar.remindtask.domain.usecase.user

import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.repository.UserRepository

class SaveHeaderType(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(headerType: HeaderType) {
        userRepository.saveHeaderType(headerType)
    }
}