package com.muffar.remindtask.screen.notes.list.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NotesHeader(
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
            )
        }
    )
}