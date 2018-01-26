package com.legalimpurity.notely.util

import android.arch.persistence.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by rkhanna on 26/1/18.
 */
class TimestampConverter {

    var df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss") as DateFormat

    @TypeConverter
    fun fromTimestamp(value: String?): Date {
        if (value != null) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return Date()
        } else {
            return Date()
        }
    }

    @TypeConverter
    fun fromDate(value: Date?): String {
        if (value != null) {
            try {
                return df.format(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        } else {
            return ""
        }
    }
}