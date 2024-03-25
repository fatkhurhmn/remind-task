package com.muffar.remindtask.screen.notes.list

import com.muffar.remindtask.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
)
