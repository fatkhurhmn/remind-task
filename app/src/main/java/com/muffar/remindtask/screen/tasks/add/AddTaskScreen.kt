package com.muffar.remindtask.screen.tasks.add

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
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.screen.tasks.add.component.AddTaskForm
import com.muffar.remindtask.screen.tasks.add.component.AddTaskTopBar
import com.muffar.remindtask.ui.common.PopUpDatePicker
import com.muffar.remindtask.ui.common.PopUpTimePicker
import com.muffar.remindtask.ui.theme.spacing
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    state: AddTaskState,
    eventFlow: SharedFlow<AddTaskViewModel.UiEvent>,
    onSaveClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDatePickerClick: (Boolean) -> Unit,
    onTimePickerClick: (Boolean) -> Unit,
    onDateSelected: (Long?) -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onPrioritySelected: (PriorityType) -> Unit,
    onNavigationBack: () -> Unit,
) {
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        eventFlow.collectLatest {
            when (it) {
                is AddTaskViewModel.UiEvent.SaveTask -> onNavigationBack()

                is AddTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHost.showSnackbar(message = it.message)
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHost) },
        topBar = {
            AddTaskTopBar(
                onCloseClick = { onNavigationBack() },
                onSaveClick = { onSaveClick() }
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
    }
}