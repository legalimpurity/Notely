package com.legalimpurity.notely.data.local.prefs

/**
 * Created by rkhanna on 25/1/18.
 */
interface PreferenceHelper
{
    fun getHeartedFilterStatus(): Boolean
    fun setHeartedFilterStatus(status: Boolean)

    fun getFavdFilterStatus(): Boolean
    fun setFavdFilterStatus(status: Boolean)
}