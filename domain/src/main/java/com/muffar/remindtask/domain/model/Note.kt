package com.muffar.remindtask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Note(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val createdAt: Long,
) : Parcelable

class InvalidNoteException(message: String) : Exception(message)