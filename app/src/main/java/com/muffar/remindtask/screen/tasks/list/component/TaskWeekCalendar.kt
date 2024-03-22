package com.muffar.remindtask.screen.tasks.list.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.yearMonth
import com.muffar.remindtask.utils.CalendarUtils.displayText
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskWeekCalendar(
    modifier: Modifier = Modifier,
    onSelectedDay: (LocalDate) -> Unit = {},
) {
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate.minusDays(500) }
    val endDate = remember { currentDate.plusDays(500) }
    var selection by remember { mutableStateOf(currentDate) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val weekCalendarState = rememberWeekCalendarState(
            startDate = startDate,
            endDate = endDate,
            firstDayOfWeek = DayOfWeek.MONDAY,
        )

        val visibleWeek = rememberFirstVisibleWeekAfterScroll(weekCalendarState)

        TopAppBar(
            title = {
                Text(
                    text = getWeekPageTitle(visibleWeek),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
                )
            },
        )

        WeekCalendar(
            state = weekCalendarState,
            dayContent = { day ->
                DayCalendarItem(
                    date = day.date,
                    isSelected = selection == day.date
                ) { localDate ->
                    if (selection != localDate) {
                        selection = localDate
                        onSelectedDay(localDate)
                    }
                }
            },
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getWeekPageTitle(week: Week): String {
    val firstDate = week.days.first().date
    val lastDate = week.days.last().date
    return when {
        firstDate.yearMonth == lastDate.yearMonth -> {
            firstDate.yearMonth.displayText()
        }

        firstDate.year == lastDate.year -> {
            "${firstDate.month.displayText(short = false)} - ${lastDate.yearMonth.displayText()}"
        }

        else -> {
            "${firstDate.yearMonth.displayText()} - ${lastDate.yearMonth.displayText()}"
        }
    }
}
