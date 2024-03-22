package com.muffar.remindtask.screen.tasks.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
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
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.TimeType
import com.muffar.remindtask.ui.common.SearchBar
import com.muffar.remindtask.ui.theme.spacing
import com.muffar.remindtask.utils.CalendarUtils.displayText
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun TaskHeader(
    modifier: Modifier = Modifier,
    status: StatusType?,
    showSearchBar: Boolean,
    searchQuery: String,
    headerType: HeaderType,
    selectedDate: LocalDate,
    selectedTime: TimeType,
    onStatusSelected: (StatusType?) -> Unit = {},
    onSelectedDay: (LocalDate) -> Unit = {},
    onSelectedTime: (TimeType) -> Unit = {},
    onHeaderTypeChange: (HeaderType) -> Unit = {},
    onShowSearchBar: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
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
                if (showSearchBar) {
                    SearchBar(searchQuery = searchQuery) {
                        onQueryChange(it)
                    }
                } else {
                    Text(
                        text = getWeekPageTitle(visibleWeek),
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
                    )
                }
            },
            actions = {
                if (showSearchBar) {
                    IconButton(
                        onClick = {
                            onShowSearchBar(false)
                            onQueryChange("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    IconButton(onClick = { onShowSearchBar(true) }) {
                        FaIcon(faIcon = FaIcons.Search, size = 20.dp)
                    }
                }
                StatusFilterMenu(
                    currentStatus = status,
                    onStatusSelected = { onStatusSelected(it) }
                )
                IconButton(
                    onClick = { onHeaderTypeChange(headerType) }
                ) {
                    if (headerType == HeaderType.CALENDAR) {
                        FaIcon(faIcon = FaIcons.EllipsisH, size = 20.dp)
                    } else {
                        FaIcon(faIcon = FaIcons.Calendar, size = 20.dp)
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
