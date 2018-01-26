package com.legalimpurity.notely.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import com.legalimpurity.notely.di.PreferenceInfo
/**
 * Created by rkhanna on 26/1/18.
 */
@Singleton
class PreferenceHelperImplementation @Inject constructor(context: Context, @PreferenceInfo prefFileName:String) : PreferenceHelper {
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

}