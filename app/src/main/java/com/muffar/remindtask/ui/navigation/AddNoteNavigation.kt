package com.muffar.remindtask.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.screen.notes.add.AddNoteEvent
import com.muffar.remindtask.screen.notes.add.AddNoteScreen
import com.muffar.remindtask.screen.notes.add.AddNoteViewModel

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

        LaunchedEffect(note) {
            if (note != null) {
                event(AddNoteEvent.OnInitState(note))
            }
        }

        AddNoteScreen(
            state = state,
            eventFlow = eventFlow,
            onTitleChange = { event(AddNoteEvent.OnTitleChange(it)) },
            onDescriptionChange = { event(AddNoteEvent.OnDescriptionChange(it)) },
            onSaveNoteClick = { event(AddNoteEvent.OnSaveNoteClick) },
            onNavigateBack = { onNavigationBack() }
        )
    }
}

fun NavController.toAddNote(note: Note? = null) {
    currentBackStackEntry?.savedStateHandle?.set(Screens.AddNote.NOTE, note)
    navigate(route = Screens.AddNote.route)
}