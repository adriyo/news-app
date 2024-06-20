package com.adriyo.newsapp.shared.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object Helper {
    fun getTimeAgo(timestamp: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val utcDate = formatter.parse(timestamp) ?: return ""

        val localCalendar = Calendar.getInstance()
        localCalendar.time = utcDate

        val now = Calendar.getInstance()

        val durationMillis = now.timeInMillis - localCalendar.timeInMillis

        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis)
        val hours = TimeUnit.MILLISECONDS.toHours(durationMillis)
        val days = TimeUnit.MILLISECONDS.toDays(durationMillis)

        return when {
            seconds < 60 -> "$seconds detik lalu"
            minutes < 60 -> "$minutes menit lalu"
            hours < 24 -> "$hours jam lalu"
            days < 30 -> "$days hari lalu"
            days < 365 -> "${days / 30} bulan lalu"
            else -> "${days / 365} tahun lalu"
        }
    }
}