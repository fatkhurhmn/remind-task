package com.muffar.remindtask.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.PriorityType.Companion.toColor

@Composable
fun TaskPriorityButton(
    modifier: Modifier = Modifier,
    selected: PriorityType,
    items: List<PriorityType>,
    cornerRadius: Int = 24,
    onItemSelection: (PriorityType) -> Unit,
) {
    var selectedItem by remember { mutableIntStateOf(selected.ordinal) }
    var itemIndex by remember { mutableIntStateOf(selected.ordinal) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp),
        colors = CardDefaults.cardColors(
            items[selectedItem].toColor()
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(items[selectedItem].toColor()),
            horizontalArrangement = Arrangement.Center
        ) {
            items.forEachIndexed { index, item ->
                itemIndex = index
                val containerButton = if (selectedItem == index) {
                    MaterialTheme.colorScheme.background
                } else {
                    items[selectedItem].toColor()
                }
                Card(
                    modifier = modifier
                        .weight(1f)
                        .padding(2.dp),
                    onClick = {
                        selectedItem = index
                        onItemSelection(item)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = containerButton,
                        contentColor = if (selectedItem == index)
                            MaterialTheme.colorScheme.scrim
                        else
                            MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = RoundedCornerShape(
                        topStartPercent = cornerRadius,
                        topEndPercent = cornerRadius,
                        bottomStartPercent = cornerRadius,
                        bottomEndPercent = cornerRadius
                    )
                ) {
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}