package com.legalimpurity.notely.ui.notesui.draweradapter

import com.legalimpurity.notely.data.local.models.others.DrawerModel

/**
 * Created by rkhanna on 27/1/18.
 */
interface DrawerAdapterListener
{
    fun onClick(drawerItem: DrawerModel)
}