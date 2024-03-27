package com.muffar.remindtask.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DescriptionTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    readOnly : Boolean = false,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        if (value.isEmpty()) {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
            )
        }
    }
}