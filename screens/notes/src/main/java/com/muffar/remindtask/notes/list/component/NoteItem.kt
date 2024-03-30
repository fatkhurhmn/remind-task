package com.muffar.remindtask.notes.list.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.theme.spacing

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    notesType: NotesType,
    onClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.small
            )
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (description.isNotEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (notesType == NotesType.GRID) 5 else 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}