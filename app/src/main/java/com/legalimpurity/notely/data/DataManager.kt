package com.legalimpurity.notely.data

import com.legalimpurity.notely.data.local.db.DatabaseHelper
import com.legalimpurity.notely.data.local.prefs.PreferenceHelper

/**
 * Created by rkhanna on 25/1/18.
 */
interface DataManager : PreferenceHelper, DatabaseHelper
{
}