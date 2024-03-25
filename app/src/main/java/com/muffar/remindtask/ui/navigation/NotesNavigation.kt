package com.muffar.remindtask.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.screen.notes.list.NotesEvent
import com.muffar.remindtask.screen.notes.list.NotesScreen
import com.muffar.remindtask.screen.notes.list.NotesViewModel

fun NavGraphBuilder.notesScreen() {
    composable(route = Screens.Notes.route) {
        val viewModel = hiltViewModel<NotesViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent

        NotesScreen(
            state = state,
            onNotesTypeChange = { event(NotesEvent.OnNotesTypeChange(it)) },
            onSearchQueryChange = { event(NotesEvent.OnSearchQueryChange(it)) },
            onShowSearchBar = { event(NotesEvent.OnShowSearchBarChange(it)) }
        )
    }
}