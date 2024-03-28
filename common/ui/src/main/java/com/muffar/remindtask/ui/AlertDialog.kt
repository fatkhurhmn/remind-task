package com.muffar.remindtask.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismissRequest()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(text = positiveButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = negativeButtonText)
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp,
        shape = MaterialTheme.shapes.small
    )
}