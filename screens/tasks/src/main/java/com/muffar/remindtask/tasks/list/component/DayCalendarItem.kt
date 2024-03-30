package com.muffar.remindtask.tasks.list.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muffar.remindtask.theme.color.MainColor
import com.muffar.remindtask.theme.spacing
import com.muffar.remindtask.utils.CalendarUtils.displayText
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DayCalendarItem(
    date: LocalDate,
    isSelected: Boolean,
    onClick: (LocalDate) -> Unit,
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) MainColor.Blue.primary else Color.Transparent,
        label = ""
    )
    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.extraSmall)
            .clip(MaterialTheme.shapes.extraSmall)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(date) }
            .background(animatedColor),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = date.dayOfWeek.displayText(),
                style = MaterialTheme.typography.bodySmall,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = DateTimeFormatter.ofPattern("dd").format(date),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
            )
        }

        if (date == LocalDate.now()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}