package com.muffar.remindtask.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.StatusType
import java.util.UUID

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String?,
    val deadline : Long,
    val priority : PriorityType,
    val status : StatusType
)