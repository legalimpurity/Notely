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

    @Query("SELECT * FROM MyNotes WHERE fav = 1 OR hearted = 1")
    fun loadByBothFilters(): List<MyNote>

    @Query("SELECT * FROM MyNotes WHERE hearted = 1")
    fun loadByHeartFilter(): List<MyNote>

    @Query("SELECT * FROM MyNotes WHERE fav = 1")
    fun loadByFavdFilter(): List<MyNote>

    @Query("SELECT * FROM MyNotes WHERE id = :id")
    fun loadCourseDataForCourseWithid(id: Int?): MyNote

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(courseData: MyNote)

    @Update()
    fun update(courseData: MyNote)
}