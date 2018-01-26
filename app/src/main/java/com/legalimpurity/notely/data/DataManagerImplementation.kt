package com.legalimpurity.notely.data

import com.legalimpurity.notely.data.local.db.DatabaseHelper
import com.legalimpurity.notely.data.local.prefs.PreferenceHelper
import javax.inject.Inject

/**
 * Created by rkhanna on 25/1/18.
 */
class DataManagerImplementation @Inject constructor(val preferencesHelper: PreferenceHelper, val databaseHelper: DatabaseHelper): DataManager
{

}