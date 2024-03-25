package com.muffar.remindtask.screen.notes.list.component

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.muffar.remindtask.domain.model.NotesType

@Composable
fun NotesHeader(
    modifier: Modifier = Modifier,
    notesType: NotesType,
    onNotesTypeChange: (NotesType) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
            )
        },
        actions = {
            if (notesType == NotesType.LIST) {
                IconButton(onClick = { onNotesTypeChange(NotesType.GRID) }) {
                    FaIcon(faIcon = FaIcons.Bars, size = 20.dp)
                }
            } else {
                IconButton(onClick = { onNotesTypeChange(NotesType.LIST) }) {
                    FaIcon(faIcon = FaIcons.GripVertical, size = 20.dp)
                }
            }
        }
    )
}