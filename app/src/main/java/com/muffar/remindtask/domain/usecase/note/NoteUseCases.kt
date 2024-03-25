package com.muffar.remindtask.domain.usecase.note

data class NoteUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote
)