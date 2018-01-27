package com.legalimpurity.notely.data

import com.legalimpurity.notely.data.local.db.DatabaseHelper
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.data.local.prefs.PreferenceHelper
import javax.inject.Inject

/**
 * Created by rkhanna on 25/1/18.
 */
class DataManagerImplementation @Inject constructor(val preferencesHelper: PreferenceHelper, val databaseHelper: DatabaseHelper): DataManager
{
    override fun getHeartedFilterStatus() = preferencesHelper.getHeartedFilterStatus()

    override fun setHeartedFilterStatus(status: Boolean) = preferencesHelper.setHeartedFilterStatus(status)

    override fun getFavdFilterStatus() = preferencesHelper.getFavdFilterStatus()

    override fun setFavdFilterStatus(status: Boolean) = preferencesHelper.setFavdFilterStatus(status)

    override fun getLocalNotes() = databaseHelper.getLocalNotes()

    override fun addANewNote(myNote: MyNote) = databaseHelper.addANewNote(myNote)
    override fun updateNote(myNote: MyNote) = databaseHelper.updateNote(myNote)
}