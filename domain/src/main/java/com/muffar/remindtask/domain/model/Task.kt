package com.muffar.remindtask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Task(
    val id: UUID? = null,
    val title: String,
    val description: String?,
    val deadline: Long,
    val priority: PriorityType,
    val status: StatusType,
) : Parcelable

class InvalidTaskException(message: String) : Exception(message)