package com.task2trip.android.Common

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    //
}

fun Calendar.toPattern(pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"): String {
    val now = Calendar.getInstance()
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(now.time)
}

fun String.toCalendar(pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"): Calendar {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        val parsedDate = Calendar.getInstance()
        parsedDate.time = formatter.parse(this)
        parsedDate
    } catch (ex: Exception) {
        Calendar.getInstance()
    }
}