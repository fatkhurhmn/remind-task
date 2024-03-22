package com.muffar.remindtask.screen.tasks.list.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.StatusType.Companion.toValue
import com.muffar.remindtask.ui.theme.spacing

@Composable
fun StatusFilterMenu(
    currentStatus: StatusType?,
    onStatusSelected: (StatusType?) -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = !showMenu }) {
        FaIcon(faIcon = FaIcons.Filter, size = 20.dp)
    }

    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
        StatusType.getAllStatusType().forEach { status ->
            val selected = if (currentStatus != status) status else null
            DropdownMenuItem(
                onClick = {
                    onStatusSelected(selected)
                    showMenu = false
                },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = status.toValue(),
                            modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                        )

                        if (currentStatus == status) {
                            FaIcon(faIcon = FaIcons.Check, size = 16.dp)
                        }
                    }
                }
            )
        }
    }
}