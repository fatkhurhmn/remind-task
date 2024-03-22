package com.muffar.remindtask.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.screen.tasks.add.AddTaskEvent
import com.muffar.remindtask.screen.tasks.add.AddTaskScreen
import com.muffar.remindtask.screen.tasks.add.AddTaskViewModel

fun NavGraphBuilder.addTaskScreen(
    onNavigationBack: () -> Unit,
) {
    composable(route = Screens.AddTask.route) {
        val viewModel = hiltViewModel<AddTaskViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent
        val eventFlow = viewModel.eventFlow

        AddTaskScreen(
            state = state,
            eventFlow = eventFlow,
            onSaveClick = { event(AddTaskEvent.OnSaveClick) },
            onTitleChange = { event(AddTaskEvent.OnTitleChange(it)) },
            onDescriptionChange = { event(AddTaskEvent.OnDescriptionChange(it)) },
            onDatePickerClick = { event(AddTaskEvent.OnDatePickerClick(it)) },
            onTimePickerClick = { event(AddTaskEvent.OnTimePickerClick(it)) },
            onDateSelected = { event(AddTaskEvent.OnDateSelected(it)) },
            onTimeSelected = { hour, minute -> event(AddTaskEvent.OnTimeSelected(hour, minute)) },
            onPrioritySelected = { event(AddTaskEvent.OnPrioritySelect(it)) },
            onNavigationBack = { onNavigationBack() }
        )
    }
}