package com.legalimpurity.notely.ui.addeditnoteui

import com.legalimpurity.notely.ui.baseui.BaseNavigator

/**
 * Created by rkhanna on 26/1/18.
 */
interface AddEditNoteNavigator : BaseNavigator
{
  fun checkNoteTitle()
  fun noteAddedOrUpdated()
}