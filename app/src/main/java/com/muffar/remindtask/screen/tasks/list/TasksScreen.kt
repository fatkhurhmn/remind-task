package com.muffar.remindtask.screen.tasks.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.muffar.remindtask.R
import com.muffar.remindtask.screen.tasks.list.component.TaskHeader
import com.muffar.remindtask.screen.tasks.list.component.TaskItem
import com.muffar.remindtask.ui.theme.spacing

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel(),
) {
    val state by viewModel.state

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TaskHeader(
            status = state.status,
            headerType = state.headerType,
            selectedDate = state.selectedDate,
            selectedTime = state.selectedTime,
            onStatusSelected = {
                viewModel.onEvent(TasksEvent.OnStatusSelected(it))
            },
            onSelectedDay = {
                viewModel.onEvent(TasksEvent.OnDateSelected(it))
            },
            onSelectedTime = {
                viewModel.onEvent(TasksEvent.OnTimeSelected(it))
            },
            onHeaderTypeChange = {
                viewModel.onEvent(TasksEvent.OnHeaderTypeChanged(state.headerType))
            }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        if (state.tasks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            ) {
                val tasks = state.tasks
                items(tasks.size) {
                    val task = tasks[it]
                    TaskItem(
                        title = task.title,
                        deadline = task.deadline,
                        priority = task.priority,
                        status = task.status
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FaIcon(
                    faIcon = FaIcons.LayerGroup,
                    tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                    size = 80.dp
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(
                    text = stringResource(R.string.no_tasks),
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp)
                )
            }
        }
    }
}