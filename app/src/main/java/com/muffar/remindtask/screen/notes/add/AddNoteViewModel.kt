package com.muffar.remindtask.screen.notes.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muffar.remindtask.domain.model.InvalidNoteException
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AddNoteState())
    val state: State<AddNoteState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentNote: Note? = null

    fun onEvent(event: AddNoteEvent) {
        when (event) {
            is AddNoteEvent.OnTitleChange -> onTitleChange(event.title)
            is AddNoteEvent.OnDescriptionChange -> onDescriptionChange(event.description)
            is AddNoteEvent.OnSaveNoteClick -> onSaveNoteClick()
            is AddNoteEvent.OnInitState -> onInitState(event.note)
            is AddNoteEvent.OnEditNoteClick -> onEditNoteClick()
            is AddNoteEvent.OnDeleteNoteClick -> onDeleteNoteClick()
            is AddNoteEvent.OnRestoreNote -> onRestoreNote()
        }
    }

    private fun onTitleChange(title: String) {
        _state.value = state.value.copy(title = title)
    }

    private fun onDescriptionChange(description: String) {
        _state.value = state.value.copy(description = description)
    }

    private fun onSaveNoteClick() {
        viewModelScope.launch {
            try {
                val id = UUID.randomUUID()
                val note = Note(
                    id = state.value.id ?: id,
                    title = state.value.title,
                    description = state.value.description
                )
                noteUseCases.addNote(note)
                if (_state.value.isAddMode) {
                    _eventFlow.emit(UiEvent.SaveNote(note))
                } else {
                    _state.value = state.value.copy(isReadOnly = true)
                }
            } catch (e: InvalidNoteException) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Couldn't save note"))
            }
        }
    }

    private fun onInitState(note: Note) {
        currentNote = note
        _state.value = AddNoteState(
            id = note.id,
            title = note.title,
            description = note.description,
            isReadOnly = true
        )
    }

    private fun onEditNoteClick() {
        _state.value = state.value.copy(isReadOnly = false)
    }

    private fun onDeleteNoteClick() {}

    private fun onRestoreNote() {
        currentNote?.let { note ->
            _state.value = AddNoteState(
                id = note.id,
                title = note.title,
                description = note.description,
                isReadOnly = true
            )
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class SaveNote(val note: Note) : UiEvent()
    }
}