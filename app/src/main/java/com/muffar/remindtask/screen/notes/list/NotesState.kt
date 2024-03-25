package com.muffar.remindtask.screen.notes.list

import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.model.NotesType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesType: NotesType = NotesType.LIST
)
