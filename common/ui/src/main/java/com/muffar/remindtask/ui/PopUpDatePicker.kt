package com.muffar.remindtask.ui

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.muffar.remindtask.resources.R
import java.time.LocalDate

@Composable
fun PopUpDatePicker(
    selectedDate: Long?,
    onDismiss: () -> Unit,
    onConfirm: (Long?) -> Unit,
) {
    val currentDate by remember { mutableStateOf(LocalDate.now()) }
    val initialDateMillis = selectedDate ?: (currentDate.toEpochDay() * 24 * 60 * 60 * 1000)
    val state = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis,
        initialDisplayedMonthMillis = initialDateMillis,
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(state.selectedDateMillis)
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
        DatePicker(
            state = state
        )
    }
}