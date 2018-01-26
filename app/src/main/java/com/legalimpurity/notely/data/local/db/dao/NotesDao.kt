package com.legalimpurity.notely.data.local.db.dao

import android.arch.persistence.room.*
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(courseData: MyNote)

    @Update()
    fun update(courseData: MyNote)
}