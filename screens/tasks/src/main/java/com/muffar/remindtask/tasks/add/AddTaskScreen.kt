package com.muffar.remindtask.tasks.add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.tasks.add.component.AddTaskForm
import com.muffar.remindtask.tasks.add.component.AddTaskTopBar
import com.muffar.remindtask.theme.spacing
import com.muffar.remindtask.ui.AlertDialog
import com.muffar.remindtask.ui.PopUpDatePicker
import com.muffar.remindtask.ui.PopUpTimePicker
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    state: AddTaskState,
    eventFlow: SharedFlow<AddTaskViewModel.UiEvent>,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDatePickerClick: (Boolean) -> Unit,
    onTimePickerClick: (Boolean) -> Unit,
    onDateSelected: (Long?) -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onPrioritySelected: (PriorityType) -> Unit,
    onNavigationBack: () -> Unit,
    onSaveTaskClick: () -> Unit,
    onEditNoteClick: () -> Unit,
    onDeleteNoteClick: () -> Unit,
    onRestoreNoteClick: () -> Unit,
    onShowDeleteDialog: (Boolean) -> Unit,
    onShowDiscardDialog: (Boolean) -> Unit,
) {
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        eventFlow.collectLatest {
            when (it) {
                is AddTaskViewModel.UiEvent.SaveTask -> onNavigationBack()
                is AddTaskViewModel.UiEvent.DeleteTask -> onNavigationBack()
                is AddTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHost.showSnackbar(message = it.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        snackbarHost = { SnackbarHost(hostState = snackbarHost) },
        topBar = {
            AddTaskTopBar(
                isAddMode = state.isAddMode,
                isReadOnly = state.isReadOnly,
                onCloseClick = {
                    if (state.isAddMode) {
                        onNavigationBack()
                    } else {
                        onShowDiscardDialog(true)
                    }
                },
                onDeleteClick = { onShowDeleteDialog(true) },
                onSaveClick = { onSaveTaskClick() },
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
            AddTaskForm(
                title = state.title,
                description = state.description,
                selectedDate = state.selectedDate,
                selectedHour = state.selectedHour,
                selectedMinute = state.selectedMinute,
                priorityType = state.priorityType,
                isReadOnly = state.isReadOnly,
                onTitleChange = { value -> onTitleChange(value) },
                onDescriptionChange = { value -> onDescriptionChange(value) },
                onDatePickerOpen = { onDatePickerClick(!state.isDatePickerOpen) },
                onTimePickerOpen = { onTimePickerClick(!state.isTimePickerOpen) },
                onPrioritySelect = { value -> onPrioritySelected(value) }
            )
        }

        if (state.isDatePickerOpen) {
            PopUpDatePicker(
                selectedDate = state.selectedDate,
                onDismiss = { onDatePickerClick(false) },
                onConfirm = { result -> onDateSelected(result) }
            )
        }

        if (state.isTimePickerOpen) {
            PopUpTimePicker(
                hour = state.selectedHour,
                minute = state.selectedMinute,
                onDismiss = { onTimePickerClick(false) },
                onConfirm = { hour, minute ->
                    onTimeSelected(hour, minute)
                }
            )
        }

        if (state.showDeleteDialog) {
            AlertDialog(
                title = stringResource(R.string.delete_task_title),
                message = stringResource(R.string.delete_task_message),
                positiveButtonText = stringResource(R.string.delete),
                negativeButtonText = stringResource(R.string.cancel),
                onConfirm = { onDeleteNoteClick() },
                onDismissRequest = { onShowDeleteDialog(false) }
            )
        }

        if (state.showDiscardDialog) {
            AlertDialog(
                title = stringResource(R.string.discard_task_title),
                message = stringResource(R.string.discard_task_message),
                positiveButtonText = stringResource(R.string.discard),
                negativeButtonText = stringResource(R.string.cancel),
                onConfirm = { onRestoreNoteClick() },
                onDismissRequest = { onShowDiscardDialog(false) }
            )
        }

        if (!state.isAddMode && !state.isReadOnly) {
            BackHandler { onShowDiscardDialog(true) }
        }
    }
}