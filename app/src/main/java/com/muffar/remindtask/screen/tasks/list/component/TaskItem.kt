package com.muffar.remindtask.screen.tasks.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.PriorityType.Companion.toColor
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.ui.theme.spacing
import com.muffar.remindtask.utils.Converter

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    deadline: Long,
    priority: PriorityType,
    status: StatusType,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(10.dp)

                    .align(Alignment.CenterEnd)
                    .background(priority.toColor())
            )
        }
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    textDecoration = if (status == StatusType.COMPLETED) TextDecoration.LineThrough else TextDecoration.None
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Row {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    FaIcon(
                        faIcon = FaIcons.CalendarRegular,
                        size = 16.dp,
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = Converter.formattedDate(deadline, "dd MMM yyyy"),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    FaIcon(
                        faIcon = FaIcons.ClockRegular,
                        size = 16.dp,
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = Converter.formattedDate(deadline, "hh:mm a"),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}