package com.muffar.remindtask.domain.model

enum class TimeType {
    PAST,
    TODAY,
    SOON,
    ALL;

    companion object {
        fun TimeType.toValue(): String {
            return when (this) {
                PAST -> "Past"
                TODAY -> "Today"
                SOON -> "Soon"
                ALL -> "All"
            }
        }

        fun String.toTimeType(): TimeType {
            return when (this) {
                "Past" -> PAST
                "Today" -> TODAY
                "Soon" -> SOON
                "All" -> ALL
                else -> TODAY
            }
        }

        fun getAllTimeTypes(): List<TimeType> {
            return listOf(PAST, TODAY, SOON, ALL)
        }
    }
}