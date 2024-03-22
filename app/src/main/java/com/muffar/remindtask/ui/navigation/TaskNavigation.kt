package com.muffar.remindtask.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.muffar.remindtask.screen.tasks.list.TasksEvent
import com.muffar.remindtask.screen.tasks.list.TasksScreen
import com.muffar.remindtask.screen.tasks.list.TasksViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.taskGraph() {
    composable(route = Screens.Tasks.route) {
        val viewModel = hiltViewModel<TasksViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent

        TasksScreen(
            state = state,
            onStatusSelected = { event(TasksEvent.OnStatusSelected(it)) },
            onTimeSelected = { event(TasksEvent.OnTimeSelected(it)) },
            onDateSelected = { event(TasksEvent.OnDateSelected(it)) },
            onHeaderTypeChange = { event(TasksEvent.OnHeaderTypeChanged(it)) },
            onTaskClick = {}
        )
    }
}