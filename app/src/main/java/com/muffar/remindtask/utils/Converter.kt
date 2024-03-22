package com.muffar.remindtask.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Converter {
    fun formattedDate(timeMillis: Long, format: String): String {
        val date = Date(timeMillis)
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }

    fun combineTimeMillis(date: Long?, hour: Int?, minute: Int?): Long? {
        if (date == null || hour == null || minute == null) {
            return null
        }
        val calendar = Calendar.getInstance().apply {
            timeInMillis = date
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        return calendar.timeInMillis
    }
}