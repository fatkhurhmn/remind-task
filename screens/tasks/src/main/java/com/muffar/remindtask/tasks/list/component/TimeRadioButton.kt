package com.muffar.remindtask.tasks.list.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.domain.model.TimeType.Companion.toValue
import com.muffar.remindtask.theme.spacing

@Composable
fun TimeRadioButton(
    modifier: Modifier = Modifier,
    selectedOption: TimeType,
    onOptionSelected: (TimeType) -> Unit,
) {
    val options = TimeType.getAllTimeTypes()

    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        options.forEach { option ->
            if (selectedOption == option) {
                Button(
                    onClick = {},
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                ) {
                    Text(text = option.toValue())
                }
            } else {
                OutlinedButton(
                    onClick = { onOptionSelected(option) },
                    modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                ) {
                    Text(text = option.toValue())
                }
            }
        }
    }
}