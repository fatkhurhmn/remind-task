package com.muffar.remindtask.screen.tasks.list.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.yearMonth
import com.muffar.remindtask.domain.model.HeaderType
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.ui.theme.spacing
import com.muffar.remindtask.utils.CalendarUtils.displayText
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskWeekCalendar(
    modifier: Modifier = Modifier,
    headerType: HeaderType,
    selectedDate: LocalDate,
    selectedTime: TimeType,
    onSelectedDay: (LocalDate) -> Unit = {},
    onSelectedTime: (TimeType) -> Unit = {},
    onHeaderTypeChange: () -> Unit = {},
) {
    val currentDate = remember { LocalDate.now() }
    val startDate = remember { currentDate.minusDays(500) }
    val endDate = remember { currentDate.plusDays(500) }

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
            actions = {
                IconButton(
                    onClick = { onHeaderTypeChange() }
                ) {
                    if (headerType == HeaderType.CALENDAR) {
                        FaIcon(FaIcons.EllipsisH)
                    } else {
                        FaIcon(FaIcons.Calendar)
                    }
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            if (headerType == HeaderType.CHIPS) {
                TimeRadioButton(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                    selectedOption = selectedTime,
                    onOptionSelected = { onSelectedTime(it) }
                )
            } else {
                WeekCalendar(
                    state = weekCalendarState,
                    dayContent = { day ->
                        DayCalendarItem(
                            date = day.date,
                            isSelected = selectedDate == day.date
                        ) { localDate ->
                            if (selectedDate != localDate) {
                                onSelectedDay(localDate)
                            }
                        }
                    },
                )
            }
        }
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
