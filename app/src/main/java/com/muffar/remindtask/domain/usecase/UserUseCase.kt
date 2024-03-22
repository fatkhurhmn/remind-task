package com.muffar.remindtask.domain.usecase

import com.muffar.remindtask.domain.model.HeaderType

interface UserUseCase {
    suspend fun saveHeaderType(headerType: HeaderType)
    suspend fun getHeaderType(): HeaderType
}