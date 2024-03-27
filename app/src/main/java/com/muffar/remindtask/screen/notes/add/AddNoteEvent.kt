package com.muffar.remindtask.screen.notes.add

import com.muffar.remindtask.domain.model.Note


sealed class AddNoteEvent {
    data class OnTitleChange(val title: String) : AddNoteEvent()
    data class OnDescriptionChange(val description: String) : AddNoteEvent()
    data object OnSaveNoteClick : AddNoteEvent()
    data class OnInitState(val note: Note) : AddNoteEvent()
}