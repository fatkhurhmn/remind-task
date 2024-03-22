package com.muffar.remindtask.screen.tasks.list

import androidx.compose.animation.animateContentSize
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
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.screen.tasks.list.component.EmptyTasks
import com.muffar.remindtask.screen.tasks.list.component.TaskHeader
import com.muffar.remindtask.screen.tasks.list.component.TaskItem
import com.muffar.remindtask.ui.theme.spacing
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
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TaskHeader(
            status = state.status,
            headerType = state.headerType,
            selectedDate = state.selectedDate,
            selectedTime = state.selectedTime,
            onStatusSelected = { onStatusSelected(it) },
            onSelectedDay = { onDateSelected(it) },
            onSelectedTime = { onTimeSelected(it) },
            onHeaderTypeChange = { onHeaderTypeChange(it) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        if (state.tasks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(),
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
                        onDelete = { onTaskDelete(task.id) }
                    )
                }
            }
        } else {
            EmptyTasks()
        }
    }
}