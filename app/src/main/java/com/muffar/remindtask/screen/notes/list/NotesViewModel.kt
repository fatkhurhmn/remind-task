package com.muffar.remindtask.screen.notes.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            noteUseCases.getNotes().collect { notes ->
                _state.value = state.value.copy(
                    notes = notes
                )
            }
        }
    }
}