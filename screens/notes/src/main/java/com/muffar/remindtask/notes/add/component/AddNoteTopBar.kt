package com.muffar.remindtask.notes.add.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.theme.color.MainColor
import com.muffar.remindtask.theme.spacing

@Composable
fun AddNoteTopBar(
    modifier: Modifier = Modifier,
    isAddMode: Boolean,
    isReadOnly: Boolean,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    val title = if (isAddMode) {
        stringResource(R.string.new_note)
    } else if (isReadOnly) {
        stringResource(R.string.note)
    } else {
        stringResource(R.string.edit_note)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = MaterialTheme.spacing.kindaSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (isReadOnly) {
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = stringResource(R.string.delete),
                    tint = MainColor.Red.primary
                )
            }
        } else {
            IconButton(onClick = { onCloseClick() }) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(R.string.close),
                    tint = MainColor.Red.primary
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
        )

        if (isReadOnly) {
            IconButton(onClick = { onEditClick() }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = stringResource(R.string.edit),
                    tint = MainColor.Green.primary
                )
            }
        } else {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(R.string.save),
                    tint = MainColor.Green.primary
                )
            }
        }
    }
}