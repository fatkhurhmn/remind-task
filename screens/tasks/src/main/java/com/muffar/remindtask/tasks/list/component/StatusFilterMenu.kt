package com.muffar.remindtask.tasks.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.StatusType.Companion.toValue
import com.muffar.remindtask.theme.color.MainColor
import com.muffar.remindtask.theme.spacing

@Composable
fun StatusFilterMenu(
    currentStatus: StatusType?,
    onStatusSelected: (StatusType?) -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        IconButton(
            onClick = { showMenu = !showMenu },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Outlined.FilterAlt,
                contentDescription = stringResource(R.string.filter),
                modifier = Modifier.size(20.dp)
            )
        }
        if (currentStatus != null) {
            Icon(
                imageVector = Icons.Rounded.Circle,
                contentDescription = null,
                tint = MainColor.Red.primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(10.dp)
            )
        }
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
                            Icon(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = stringResource(R.string.check),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}