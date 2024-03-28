package com.muffar.remindtask.domain.model

enum class HeaderType {
    CALENDAR,
    CHIPS;

    companion object {
        fun HeaderType.toValue(): String {
            return when (this) {
                CALENDAR -> "Calendar"
                CHIPS -> "Chips"
            }
        }

        fun String.toHeaderType(): HeaderType {
            return when (this) {
                "Calendar" -> CALENDAR
                "Chips" -> CHIPS
                else -> CALENDAR
            }
        }
    }
}