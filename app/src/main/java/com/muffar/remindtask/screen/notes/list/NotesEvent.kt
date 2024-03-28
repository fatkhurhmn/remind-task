package com.muffar.remindtask.screen.notes.list

import com.muffar.remindtask.model.NotesType

sealed class NotesEvent {
    data class OnNotesTypeChange(val notesType: NotesType) : NotesEvent()
    data class OnSearchQueryChange(val query: String) : NotesEvent()
    data class OnShowSearchBarChange(val showSearchBar: Boolean) : NotesEvent()
}