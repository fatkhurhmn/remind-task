package com.muffar.remindtask.screen.notes.list

import com.muffar.remindtask.model.Note
import com.muffar.remindtask.model.NotesType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesType: NotesType = NotesType.LIST,
    val searchQuery: String = "",
    val showSearchBar: Boolean = false
)
