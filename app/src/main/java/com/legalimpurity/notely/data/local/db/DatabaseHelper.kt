package com.legalimpurity.notely.data.local.db

import com.legalimpurity.notely.data.local.models.local.MyNote
import io.reactivex.Observable

/**
 * Created by rkhanna on 25/1/18.
 */
interface DatabaseHelper
{
    fun addANewNote(myNote: MyNote): Observable<Boolean>
    fun updateNote(myNote: MyNote): Observable<Boolean>
    fun getLocalNotes(shouldBeHearted:Boolean, shouldBeFavd:Boolean): Observable<List<MyNote>>
}