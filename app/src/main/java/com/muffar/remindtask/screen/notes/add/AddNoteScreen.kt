package com.muffar.remindtask.screen.notes.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.muffar.remindtask.screen.notes.add.component.AddNoteForm
import com.muffar.remindtask.screen.notes.add.component.AddNoteTopBar
import com.muffar.remindtask.ui.theme.spacing
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    state: AddNoteState,
    eventFlow: SharedFlow<AddNoteViewModel.UiEvent>,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveNoteClick: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        eventFlow.collectLatest {
            when (it) {
                is AddNoteViewModel.UiEvent.SaveNote -> onNavigateBack()
                is AddNoteViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHost.showSnackbar(message = it.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHost) },
        topBar = {
            AddNoteTopBar(
                onCloseClick = { onNavigateBack() },
                onSaveClick = { onSaveNoteClick() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(MaterialTheme.spacing.medium)
        ) {
            AddNoteForm(
                title = state.title,
                description = state.description,
                onTitleChange = onTitleChange,
                onDescriptionChange = onDescriptionChange,
            )
        }
    }
}