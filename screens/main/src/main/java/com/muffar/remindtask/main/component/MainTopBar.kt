package com.muffar.remindtask.main.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "RemindTask",
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)
    )
}