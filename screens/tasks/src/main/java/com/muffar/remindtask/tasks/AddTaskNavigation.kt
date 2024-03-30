package com.muffar.remindtask.tasks

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.navigation.Screens
import com.muffar.remindtask.tasks.add.AddTaskEvent
import com.muffar.remindtask.tasks.add.AddTaskScreen
import com.muffar.remindtask.tasks.add.AddTaskViewModel

fun NavGraphBuilder.addTaskScreen(
    navController: NavController,
    onNavigationBack: () -> Unit,
) {
    composable(route = Screens.AddTask.route) {
        val viewModel = hiltViewModel<AddTaskViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent
        val eventFlow = viewModel.eventFlow

        val task =
            navController.previousBackStackEntry?.savedStateHandle?.get<Task>(Screens.AddTask.TASK)

        LaunchedEffect(Unit) {
            event(AddTaskEvent.OnInitState(task))
        }

        AddTaskScreen(
            state = state,
            eventFlow = eventFlow,
            onTitleChange = { event(AddTaskEvent.OnTitleChange(it)) },
            onDescriptionChange = {
                event(
                    AddTaskEvent.OnDescriptionChange(
                        it
                    )
                )
            },
            onDatePickerClick = {
                event(
                    AddTaskEvent.OnDatePickerClick(
                        it
                    )
                )
            },
            onTimePickerClick = {
                event(
                    AddTaskEvent.OnTimePickerClick(
                        it
                    )
                )
            },
            onDateSelected = { event(AddTaskEvent.OnDateSelected(it)) },
            onTimeSelected = { hour, minute ->
                event(
                    AddTaskEvent.OnTimeSelected(
                        hour,
                        minute
                    )
                )
            },
            onPrioritySelected = {
                event(
                    AddTaskEvent.OnPrioritySelect(
                        it
                    )
                )
            },
            onNavigationBack = { onNavigationBack() },
            onSaveTaskClick = { event(AddTaskEvent.OnSaveTaskClick) },
            onEditNoteClick = { event(AddTaskEvent.OnEditTaskClick) },
            onDeleteNoteClick = { event(AddTaskEvent.OnDeleteTaskClick) },
            onRestoreNoteClick = { event(AddTaskEvent.OnRestoreTask) },
            onShowDeleteDialog = {
                event(
                    AddTaskEvent.OnShowDeleteDialog(
                        it
                    )
                )
            },
            onShowDiscardDialog = {
                event(
                    AddTaskEvent.OnShowDiscardDialog(
                        it
                    )
                )
            }
        )
    }
}

fun NavController.toAddTask(task: Task? = null) {
    currentBackStackEntry?.savedStateHandle?.set(Screens.AddTask.TASK, task)
    navigate(route = Screens.AddTask.route)
}