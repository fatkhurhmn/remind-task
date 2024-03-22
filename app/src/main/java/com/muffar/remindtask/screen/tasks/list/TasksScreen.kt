package com.muffar.remindtask.screen.tasks.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.muffar.remindtask.screen.tasks.list.component.TaskItem
import com.muffar.remindtask.screen.tasks.list.component.TaskWeekCalendar
import com.muffar.remindtask.ui.theme.spacing

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    Column {
        TaskWeekCalendar(
            onSelectedDay = {
                viewModel.onEvent(TasksEvent.SelectDate(it))
            }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
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
}