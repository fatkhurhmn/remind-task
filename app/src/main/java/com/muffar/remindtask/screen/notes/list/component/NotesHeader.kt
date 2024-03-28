package com.muffar.remindtask.screen.notes.list.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.domain.model.NotesType
import com.muffar.remindtask.ui.SearchBar

@Composable
fun NotesHeader(
    modifier: Modifier = Modifier,
    notesType: NotesType,
    searchQuery: String,
    showSearchBar: Boolean,
    onNotesTypeChange: (NotesType) -> Unit,
    onQueryChange: (String) -> Unit,
    onShowSearchBar: (Boolean) -> Unit,
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
                    text = stringResource(R.string.notes_menu),
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
                        contentDescription = stringResource(R.string.notes_menu),
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                IconButton(onClick = { onShowSearchBar(true) }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }

            if (notesType == NotesType.LIST) {
                IconButton(onClick = { onNotesTypeChange(NotesType.GRID) }) {
                    Icon(
                        imageVector = Icons.Outlined.GridView,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            } else {
                IconButton(onClick = { onNotesTypeChange(NotesType.LIST) }) {
                    Icon(
                        imageVector = Icons.Outlined.ViewAgenda,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }
        }
    )
}