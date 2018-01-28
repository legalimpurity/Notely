package com.legalimpurity.notely.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by rajatkhanna on 28/01/18.
 */


class RelativeWeekDayUtil(private val mCalendar: Calendar) {


    val timeFormat = SimpleDateFormat("hh:mm a")

    private val weekDay: String
        get() {
            val dayFormat = SimpleDateFormat("EEEE")
            return dayFormat.format(mCalendar.timeInMillis) + " at " + timeFormat.format(mCalendar.timeInMillis)
        }

    private val myFormatdate: String
        get() {
            val dayFormat = SimpleDateFormat("MMM dd")
            return dayFormat.format(mCalendar.timeInMillis) + " at " + timeFormat.format(mCalendar.timeInMillis)
        }

    override fun toString(): String {
        val today = Calendar.getInstance(Locale.getDefault())
        val dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR)
        val diff = Math.abs(dayOfYear - today.get(Calendar.DAY_OF_YEAR))
        return when {
            diff < 2 -> getRelativeDay(today)
            diff < 7 -> weekDay
            else -> myFormatdate
        }
    }

    private fun getRelativeDay(today: Calendar): String = DateUtils.getRelativeTimeSpanString(
                mCalendar.timeInMillis,
                today.timeInMillis,
                DateUtils.DAY_IN_MILLIS,
                DateUtils.FORMAT_SHOW_WEEKDAY).toString() + " at " + timeFormat.format(mCalendar.timeInMillis)
}

