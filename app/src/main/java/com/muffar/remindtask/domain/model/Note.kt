package com.muffar.remindtask.domain.model

import java.util.UUID

data class Note(
    val id: UUID? = null,
    val title: String,
    val description: String
)
