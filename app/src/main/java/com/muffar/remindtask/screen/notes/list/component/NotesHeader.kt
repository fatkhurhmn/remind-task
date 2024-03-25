package com.muffar.remindtask.screen.notes.list.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
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
import com.muffar.remindtask.ui.common.SearchBar

@Composable
fun NotesHeader(
    modifier: Modifier = Modifier,
    notesType: NotesType,
    searchQuery: String,
    showSearchBar: Boolean,
    onNotesTypeChange: (NotesType) -> Unit,
    onQueryChange: (String) -> Unit,
    onShowSearchBar: (Boolean) -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (showSearchBar) {
                SearchBar(searchQuery = searchQuery) {
                    onQueryChange(it)
                }
            } else {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
                )
            }
        },
        actions = {

            if (showSearchBar) {
                IconButton(
                    onClick = {
                        onShowSearchBar(false)
                        onQueryChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                IconButton(onClick = { onShowSearchBar(true) }) {
                    FaIcon(faIcon = FaIcons.Search, size = 20.dp)
                }
            }

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