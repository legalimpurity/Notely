package com.legalimpurity.notely.data

import com.legalimpurity.notely.data.local.db.DatabaseHelper
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.data.local.prefs.PreferenceHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rkhanna on 25/1/18.
 */
class DataManagerImplementation @Inject constructor(val preferencesHelper: PreferenceHelper, val databaseHelper: DatabaseHelper): DataManager
{
    override fun getLocalNotes() = databaseHelper.getLocalNotes()

    override fun addANewNote(myNote: MyNote) = databaseHelper.addANewNote(myNote)
}