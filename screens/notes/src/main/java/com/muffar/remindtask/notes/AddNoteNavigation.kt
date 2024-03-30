package com.muffar.remindtask.notes

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.navigation.Screens
import com.muffar.remindtask.notes.add.AddNoteEvent
import com.muffar.remindtask.notes.add.AddNoteScreen
import com.muffar.remindtask.notes.add.AddNoteViewModel

fun NavGraphBuilder.addNoteScreen(
    navController: NavController,
    onNavigationBack: () -> Unit,
) {
    composable(route = Screens.AddNote.route) {
        val viewModel = hiltViewModel<AddNoteViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent
        val eventFlow = viewModel.eventFlow

        val note =
            navController.previousBackStackEntry?.savedStateHandle?.get<Note>(Screens.AddNote.NOTE)

        LaunchedEffect(Unit) {
            event(AddNoteEvent.OnInitState(note))
        }

        AddNoteScreen(
            state = state,
            eventFlow = eventFlow,
            onTitleChange = { event(AddNoteEvent.OnTitleChange(it)) },
            onDescriptionChange = {
                event(
                    AddNoteEvent.OnDescriptionChange(
                        it
                    )
                )
            },
            onSaveNoteClick = { event(AddNoteEvent.OnSaveNoteClick) },
            onNavigateBack = { onNavigationBack() },
            onEditNoteClick = { event(AddNoteEvent.OnEditNoteClick) },
            onDeleteNoteClick = { event(AddNoteEvent.OnDeleteNoteClick) },
            onRestoreNoteClick = { event(AddNoteEvent.OnRestoreNote) },
            onShowDeleteDialog = {
                event(
                    AddNoteEvent.OnShowDeleteDialog(
                        it
                    )
                )
            },
            onShowDiscardDialog = {
                event(
                    AddNoteEvent.OnShowDiscardDialog(
                        it
                    )
                )
            }
        )
    }
}

fun NavController.toAddNote(note: Note? = null) {
    currentBackStackEntry?.savedStateHandle?.set(Screens.AddNote.NOTE, note)
    navigate(route = Screens.AddNote.route)
}