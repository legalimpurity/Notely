package com.legalimpurity.notely.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.legalimpurity.notely.data.local.db.dao.NotesDao
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.util.TimestampConverter

/**
 * Created by rkhanna on 26/1/18.
 */
@Database(entities = [MyNote::class], version = 1)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}