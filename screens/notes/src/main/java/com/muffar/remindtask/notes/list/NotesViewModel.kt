package com.muffar.remindtask.notes.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.domain.usecase.note.NoteUseCases
import com.muffar.remindtask.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var notes = emptyList<Note>()

    init {
        initNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.OnNotesTypeChange -> onNotesTypeChange(event.notesType)
            is NotesEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)
            is NotesEvent.OnShowSearchBarChange -> onShowSearchBarChange(event.showSearchBar)
        }
    }

    private fun initNotes() {
        viewModelScope.launch {
            noteUseCases.getNotes().collectLatest { notesList ->
                notes = notesList
                userUseCase.getNotesType().collectLatest { notesType ->
                    _state.value = state.value.copy(notesType = notesType)
                    filterNotes()
                }
            }
        }
    }

    private fun filterNotes() {
        val filteredNotes = noteUseCases.getNotes.filter(notes, state.value.searchQuery)
        _state.value = state.value.copy(notes = filteredNotes)
    }

    private fun onNotesTypeChange(notesType: NotesType) {
        viewModelScope.launch {
            userUseCase.saveNotesType(notesType)
        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.value = state.value.copy(searchQuery = query)
        filterNotes()
    }

    private fun onShowSearchBarChange(showSearchBar: Boolean) {
        _state.value = state.value.copy(showSearchBar = showSearchBar)
    }
}