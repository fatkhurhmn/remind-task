package com.muffar.remindtask.screen.notes.add

import java.util.UUID

data class AddNoteState(
    val id: UUID? = null,
    val title: String = "",
    val description: String = "",
    val isAddMode: Boolean = id == null,
    val isReadOnly : Boolean = false
)
