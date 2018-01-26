package com.legalimpurity.notely.ui.addviewnoteactivity

import com.legalimpurity.notely.ui.baseui.BaseNavigator

/**
 * Created by rkhanna on 26/1/18.
 */
interface AddViewNoteNavigator : BaseNavigator
{
  fun checkNoteTitle()
  fun noteAddedOrUpdated()
}