package com.task2trip.android.Common

import android.widget.EditText
import com.task2trip.android.Model.Task.TaskStatus
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    //
}

fun Calendar.toPattern(pattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(this.time)
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

fun EditText.toInt(): Int {
    return try {
        val strValue: String = this.text.toString()
        strValue.toInt()
    } catch (ex: Exception) {
        0
    }
}


fun TaskStatus.getMyName(): String {
    val local = Locale.getDefault().language
    return when(this) {
        TaskStatus.NEW -> {
            when(local) {
                "ru" -> {"Новая"}
                "en" -> {"New"}
                else -> {"Новая"}
            }
        }
        TaskStatus.IN_PROGRESS -> {
            when(local) {
                "ru" -> {"Выполняется"}
                "en" -> {"In progress"}
                else -> {"Выполняется"}
            }
        }
        TaskStatus.FINISHED -> {
            when(local) {
                "ru" -> {"Завершена"}
                "en" -> {"finished"}
                else -> {"Завершена"}
            }
        }
        TaskStatus.CANCELED -> {
            when(local) {
                "ru" -> {"Отменена"}
                "en" -> {"Canceled"}
                else -> {"Отменена"}
            }
        }
    }
}