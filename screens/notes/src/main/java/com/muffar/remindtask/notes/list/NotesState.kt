package com.muffar.remindtask.notes.list

import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.model.NotesType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesType: NotesType = NotesType.LIST,
    val searchQuery: String = "",
    val showSearchBar: Boolean = false
)
