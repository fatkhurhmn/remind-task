package com.muffar.remindtask.domain.repository

import com.muffar.remindtask.domain.model.HeaderType
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveHeaderType(headerType: HeaderType)
    fun getHeaderType(): Flow<HeaderType>
}