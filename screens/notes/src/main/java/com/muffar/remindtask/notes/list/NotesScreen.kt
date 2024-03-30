package com.muffar.remindtask.notes.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.notes.list.component.NoteItem
import com.muffar.remindtask.notes.list.component.NotesHeader
import com.muffar.remindtask.ui.EmptyResult
import com.muffar.remindtask.theme.spacing

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    state: NotesState,
    onNotesTypeChange: (NotesType) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onShowSearchBar: (Boolean) -> Unit,
    onNoteClick : (Note) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        NotesHeader(
            notesType = state.notesType,
            searchQuery = state.searchQuery,
            showSearchBar = state.showSearchBar,
            onNotesTypeChange = onNotesTypeChange,
            onQueryChange = onSearchQueryChange,
            onShowSearchBar = onShowSearchBar
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        if (state.notes.isNotEmpty()) {
            val grid = if (state.notesType == NotesType.GRID) 2 else 1
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(grid),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalItemSpacing = MaterialTheme.spacing.small
            ) {
                val notes = state.notes
                items(notes.size, key = { notes[it].id.toString() }) {
                    val note = notes[it]
                    NoteItem(
                        title = note.title,
                        description = note.description,
                        notesType = state.notesType,
                        onClick = { onNoteClick(note) }
                    )
                }
            }
        } else {
            EmptyResult(message = "No notes found")
        }
    }
}