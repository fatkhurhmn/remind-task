package com.muffar.remindtask.domain.model

enum class TimeType {
    EXPIRED,
    TODAY,
    UPCOMING,
    ALL;

    companion object {
        fun TimeType.toValue(): String {
            return when (this) {
                EXPIRED -> "Expired"
                TODAY -> "Today"
                UPCOMING -> "Upcoming"
                ALL -> "All"
            }
        }

        fun String.toTimeType(): TimeType {
            return when (this) {
                "Expired" -> EXPIRED
                "Today" -> TODAY
                "Upcoming" -> UPCOMING
                "All" -> ALL
                else -> TODAY
            }
        }

        fun getAllTimeTypes(): List<TimeType> {
            return listOf(EXPIRED, TODAY, UPCOMING, ALL)
        }
    }
}