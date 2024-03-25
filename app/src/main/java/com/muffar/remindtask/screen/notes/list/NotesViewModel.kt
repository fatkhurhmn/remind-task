package com.muffar.remindtask.screen.notes.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.domain.usecase.note.NoteUseCases
import com.muffar.remindtask.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        initNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.OnNotesTypeChange -> onNotesTypeChange(event.notesType)
        }
    }

    private fun onNotesTypeChange(notesType: NotesType) {
        viewModelScope.launch {
            userUseCase.saveNotesType(notesType)
        }
    }

    private fun initNotes(){
        viewModelScope.launch {
            noteUseCases.getNotes().collect { notes ->
                userUseCase.getNotesType().collect { notesType ->
                    _state.value = state.value.copy(notesType = notesType, notes = notes)
                }
            }
        }
    }
}