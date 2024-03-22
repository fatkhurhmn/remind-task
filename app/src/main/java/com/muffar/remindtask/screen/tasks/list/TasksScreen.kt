package com.muffar.remindtask.screen.tasks.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.muffar.remindtask.screen.tasks.list.component.TaskItem
import com.muffar.remindtask.ui.theme.spacing

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        val tasks = state.value.tasks
        items(tasks.size) {
            val task = tasks[it]
            TaskItem(
                title = task.title,
                deadline = task.deadline,
                priority = task.priority
            )
        }
    }
}