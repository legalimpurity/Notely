package com.legalimpurity.notely.ui.notesui.notesadapter

import android.view.View
import com.legalimpurity.notely.data.local.models.local.MyNote

/**
 * Created by rkhanna on 26/1/18.
 */
interface NotesAdapterListener
{
    fun onClick(myNote: MyNote, view: View)
    fun onFavd(myNote: MyNote, view: View)
    fun onHearted(myNote: MyNote, view: View)
    fun onSwipped(myNote: MyNote, pos: Int)
}