package com.muffar.remindtask.screen.notes.add.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.muffar.remindtask.R
import com.muffar.remindtask.ui.theme.color.MainColor
import com.muffar.remindtask.ui.theme.spacing

@Composable
fun AddNoteTopBar(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = MaterialTheme.spacing.kindaSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { onCloseClick() }) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(R.string.close),
                tint = MainColor.Red.primary
            )
        }
        Text(
            text = stringResource(R.string.new_note),
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
        )
        IconButton(onClick = { onSaveClick() }) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = stringResource(R.string.save),
                tint = MainColor.Green.primary
            )
        }
    }
}