package com.muffar.remindtask.notes.add.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.muffar.remindtask.resources.R
import com.muffar.remindtask.ui.DescriptionTextField
import com.muffar.remindtask.ui.TitleTextField
import com.muffar.remindtask.theme.spacing

@Composable
fun AddNoteForm(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    readOnly: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TitleTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            value = title,
            readOnly = readOnly,
            hint = stringResource(R.string.add_title),
            onValueChange = { onTitleChange(it) }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        DescriptionTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            value = description,
            readOnly = readOnly,
            hint = stringResource(R.string.add_description),
            onValueChange = { onDescriptionChange(it) }
        )
    }
}