package com.example.myapplication.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class DataTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}