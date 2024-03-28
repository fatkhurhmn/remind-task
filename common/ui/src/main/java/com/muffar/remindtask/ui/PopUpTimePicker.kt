package com.muffar.remindtask.ui

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.muffar.remindtask.resources.R
import java.util.Calendar

@Composable
fun PopUpTimePicker(
    hour: Int?,
    minute: Int?,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    val calendar = remember { Calendar.getInstance() }
    val currentSelectedHour = hour ?: calendar.get(Calendar.HOUR_OF_DAY)
    val currentSelectedMinute = minute ?: calendar.get(Calendar.MINUTE)

    val state = rememberTimePickerState(
        initialHour = currentSelectedHour,
        initialMinute = currentSelectedMinute,
    )

    TimePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(state.hour, state.minute)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    ) {
        TimePicker(state = state)
    }
}