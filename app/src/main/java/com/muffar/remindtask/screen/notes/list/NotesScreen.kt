package com.muffar.remindtask.screen.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.screen.notes.list.component.NoteItem
import com.muffar.remindtask.screen.notes.list.component.NotesHeader
import com.muffar.remindtask.ui.common.EmptyResult
import com.muffar.remindtask.ui.theme.spacing

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    state: NotesState,
    onNotesTypeChange: (NotesType) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        NotesHeader(
            notesType = state.notesType,
            onNotesTypeChange = onNotesTypeChange
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        if (state.notes.isNotEmpty()) {
            val grid = if (state.notesType == NotesType.GRID) 2 else 1
            LazyVerticalGrid(
                columns = GridCells.Fixed(grid),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                val notes = state.notes
                items(notes.size, key = { notes[it].id.toString() }) {
                    val note = notes[it]
                    NoteItem(
                        title = note.title,
                        description = note.description,
                        notesType = state.notesType,
                        onClick = { }
                    )
                }
            }
        } else {
            EmptyResult(message = "No notes found")
        }
    }
}