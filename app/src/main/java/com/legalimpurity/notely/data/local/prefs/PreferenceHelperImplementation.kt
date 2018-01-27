package com.legalimpurity.notely.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.legalimpurity.notely.di.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton
/**
 * Created by rkhanna on 26/1/18.
 */
@Singleton
class PreferenceHelperImplementation @Inject constructor(context: Context, @PreferenceInfo prefFileName:String) : PreferenceHelper {
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    private val PREF_HEARETED_FILTER = "PREF_HEARETED_FILTER"
    private val PREF_FAVD_FILTER = "PREF_FAVD_FILTER"

    override fun getHeartedFilterStatus(): Boolean
    {
        sharedPreferences?.let {
            return it.getBoolean(PREF_HEARETED_FILTER,false)
        }
        return false
    }

    override fun setHeartedFilterStatus(status: Boolean) {
        sharedPreferences?.edit()?.putBoolean(PREF_HEARETED_FILTER,status)?.apply()
    }

    override fun getFavdFilterStatus(): Boolean
    {
        sharedPreferences?.let {
            return it.getBoolean(PREF_FAVD_FILTER,false)
        }
        return false
    }

    override fun setFavdFilterStatus(status: Boolean) {
        sharedPreferences?.edit()?.putBoolean(PREF_FAVD_FILTER,status)?.apply()
    }
}