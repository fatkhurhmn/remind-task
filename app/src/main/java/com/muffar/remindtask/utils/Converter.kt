package com.muffar.remindtask.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object Converter {
    fun formattedDate(timeMillis: Long, format: String): String {
        val date = Date(timeMillis)
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }
}