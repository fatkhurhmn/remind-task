package com.muffar.remindtask.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.theme.spacing
import com.muffar.remindtask.utils.Converter

@Composable
fun TaskDateButton(
    modifier: Modifier = Modifier,
    selectedDate: Long?,
    readOnly: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth()
            .then(if (readOnly) Modifier else Modifier.clickable { onClick() })
            .padding(MaterialTheme.spacing.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            Icon(
                imageVector = Icons.Rounded.CalendarToday,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = stringResource(R.string.date),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val date = if (selectedDate != null) {
                Converter.formattedDate(selectedDate, "MMM dd, yyyy")
            } else {
                stringResource(R.string.select_date)
            }
            Text(
                text = date,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
            )

            if (!readOnly) {
                Icon(
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}