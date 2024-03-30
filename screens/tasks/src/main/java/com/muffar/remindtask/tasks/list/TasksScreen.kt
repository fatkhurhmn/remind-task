package com.muffar.remindtask.tasks.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.tasks.list.component.TaskHeader
import com.muffar.remindtask.tasks.list.component.TaskItem
import com.muffar.remindtask.ui.AlertDialog
import com.muffar.remindtask.ui.EmptyResult
import com.muffar.remindtask.theme.spacing
import java.time.LocalDate
import java.util.UUID

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    state: TasksState,
    onStatusSelected: (StatusType?) -> Unit,
    onTimeSelected: (TimeType) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onHeaderTypeChange: (HeaderType) -> Unit,
    onTaskClick: (Task) -> Unit,
    onTaskCheck: (Task) -> Unit,
    onTaskDelete: (UUID?) -> Unit,
    onShowDialog: (Boolean, Task?) -> Unit,
    onShowSearchBar: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TaskHeader(
            status = state.status,
            showSearchBar = state.showSearchBar,
            searchQuery = state.searchQuery,
            headerType = state.headerType,
            selectedDate = state.selectedDate,
            selectedTime = state.selectedTime,
            onStatusSelected = { onStatusSelected(it) },
            onSelectedDay = { onDateSelected(it) },
            onSelectedTime = { onTimeSelected(it) },
            onHeaderTypeChange = { onHeaderTypeChange(it) },
            onShowSearchBar = { onShowSearchBar(it) },
            onQueryChange = { onQueryChange(it) }
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
                items(tasks.size, key = { tasks[it].id.toString() }) {
                    val task = tasks[it]
                    TaskItem(
                        title = task.title,
                        deadline = task.deadline,
                        priority = task.priority,
                        status = task.status,
                        onClick = { onTaskClick(task) },
                        onCheck = { onTaskCheck(task) },
                        onDelete = { onShowDialog(true, task) }
                    )
                }
            }
        } else {
            EmptyResult(message = "No tasks found")
        }
    }

    if (state.showDialog) {
        AlertDialog(
            title = stringResource(R.string.delete_task_title),
            message = stringResource(R.string.delete_task_message),
            positiveButtonText = stringResource(R.string.delete),
            negativeButtonText = stringResource(R.string.cancel),
            onConfirm = { onTaskDelete(state.selectedTask?.id) },
            onDismissRequest = { onShowDialog(false, null) }
        )
    }
}