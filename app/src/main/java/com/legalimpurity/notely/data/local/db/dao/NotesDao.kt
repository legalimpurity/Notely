package com.legalimpurity.notely.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.legalimpurity.notely.data.local.models.local.MyNote

/**
 * Created by rkhanna on 26/1/18.
 */
@Dao
interface NotesDao {

    @Query("SELECT * FROM MyNotes")
    fun loadAll(): List<MyNote>

    @Query("SELECT * FROM MyNotes WHERE id = :id")
    fun loadCourseDataForCourseWithCCD(id: Int?): MyNote

    @Insert()
    fun insert(courseData: MyNote)

}