package com.muffar.remindtask.domain.model

import java.util.UUID

data class Task(
    val id : UUID? = null,
    val title: String,
    val description: String?,
    val deadline: Long,
    val priority: PriorityType,
    val status : StatusType
)