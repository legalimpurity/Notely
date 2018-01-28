package com.legalimpurity.notely.ui.notesui

import com.legalimpurity.notely.ui.baseui.BaseNavigator

/**
 * Created by rkhanna on 26/1/18.
 */
interface NotesActivityNavigator : BaseNavigator
{
    fun refreshAdapter()
    fun clearFilterChangeStatusAndCloseDrawer()
    fun notifyAdapterToRemove(pos:Int)
    fun closeDrawer()
}