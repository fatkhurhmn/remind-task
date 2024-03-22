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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.muffar.remindtask.screen.tasks.add.component.AddTaskForm
import com.muffar.remindtask.screen.tasks.add.component.AddTaskTopBar
import com.muffar.remindtask.ui.common.PopUpDatePicker
import com.muffar.remindtask.ui.common.PopUpTimePicker
import com.muffar.remindtask.ui.theme.spacing
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val state by viewModel.state
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is AddTaskViewModel.UiEvent.SaveTask -> navController.navigateUp()
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
                onCloseClick = { navController.navigateUp() },
                onSaveClick = { viewModel.onEvent(AddTaskEvent.OnSaveClick) }
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
                onTitleChange = { value -> viewModel.onEvent(AddTaskEvent.OnTitleChange(value)) },
                onDescriptionChange = { value ->
                    viewModel.onEvent(
                        AddTaskEvent.OnDescriptionChange(
                            value
                        )
                    )
                },
                onDatePickerOpen = { viewModel.onEvent(AddTaskEvent.OnDatePickerClick(!state.isDatePickerOpen)) },
                onTimePickerOpen = { viewModel.onEvent(AddTaskEvent.OnTimePickerClick(!state.isTimePickerOpen)) },
                onPrioritySelect = { value -> viewModel.onEvent(AddTaskEvent.OnPrioritySelect(value)) }
            )
        }

        if (state.isDatePickerOpen) {
            PopUpDatePicker(
                onDismiss = { viewModel.onEvent(AddTaskEvent.OnDatePickerClick(false)) },
                onConfirm = { result ->
                    viewModel.onEvent(AddTaskEvent.OnDateSelected(result))
                }
            )
        }

        if (state.isTimePickerOpen) {
            PopUpTimePicker(
                onDismiss = { viewModel.onEvent(AddTaskEvent.OnTimePickerClick(false)) },
                onConfirm = { hour, minute ->
                    viewModel.onEvent(AddTaskEvent.OnTimeSelected(hour, minute))
                }
            )
        }
    }
}