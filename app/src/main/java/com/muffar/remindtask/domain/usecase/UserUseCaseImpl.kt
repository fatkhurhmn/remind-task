package com.muffar.remindtask.domain.usecase

import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
) : UserUseCase {
    override suspend fun saveHeaderType(headerType: HeaderType) =
        userRepository.saveHeaderType(headerType)

    override suspend fun getHeaderType(): HeaderType = userRepository.getHeaderType().first()
}