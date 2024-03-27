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
import androidx.compose.ui.res.stringResource
import com.muffar.remindtask.R
import com.muffar.remindtask.screen.notes.add.component.AddNoteForm
import com.muffar.remindtask.screen.notes.add.component.AddNoteTopBar
import com.muffar.remindtask.ui.common.AlertDialog
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
    onEditNoteClick: () -> Unit,
    onDeleteNoteClick: () -> Unit,
    onRestoreNoteClick: () -> Unit,
    onShowDialog: (Boolean) -> Unit,
) {
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        eventFlow.collectLatest {
            when (it) {
                is AddNoteViewModel.UiEvent.SaveNote -> onNavigateBack()
                is AddNoteViewModel.UiEvent.DeleteNote -> onNavigateBack()
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
                isAddMode = state.isAddMode,
                isReadOnly = state.isReadOnly,
                onCloseClick = {
                    if (state.isAddMode) {
                        onNavigateBack()
                    } else {
                        onRestoreNoteClick()
                    }
                },
                onDeleteClick = { onShowDialog(true) },
                onSaveClick = { onSaveNoteClick() },
                onEditClick = { onEditNoteClick() }
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
                readOnly = state.isReadOnly,
                onTitleChange = onTitleChange,
                onDescriptionChange = onDescriptionChange
            )
        }
    }

    if (state.showDialog) {
        AlertDialog(
            title = stringResource(R.string.delete_note_title),
            message = stringResource(R.string.delete_note_message),
            positiveButtonText = stringResource(R.string.delete),
            negativeButtonText = stringResource(R.string.cancel),
            onConfirm = { onDeleteNoteClick() },
            onDismissRequest = { onShowDialog(false) }
        )
    }
}