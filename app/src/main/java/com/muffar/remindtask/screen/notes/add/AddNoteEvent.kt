package com.muffar.remindtask.screen.notes.add

import com.muffar.remindtask.domain.model.Note


sealed class AddNoteEvent {
    data class OnTitleChange(val title: String) : AddNoteEvent()
    data class OnDescriptionChange(val description: String) : AddNoteEvent()
    data class OnShowDialog(val show: Boolean) : AddNoteEvent()
    data object OnSaveNoteClick : AddNoteEvent()
    data object OnEditNoteClick : AddNoteEvent()
    data object OnDeleteNoteClick : AddNoteEvent()
    data class OnInitState(val note: Note?) : AddNoteEvent()
    data object OnRestoreNote : AddNoteEvent()
}