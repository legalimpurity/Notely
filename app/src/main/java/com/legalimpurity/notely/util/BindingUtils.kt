package com.legalimpurity.notely.util

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.data.local.models.others.DrawerModel
import com.legalimpurity.notely.ui.notesui.draweradapter.DrawerAdapter
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter

/**
 * Created by rkhanna on 26/1/18.
 */
@BindingAdapter("adapter")
fun notesDataAdapterBinding(recyclerView: RecyclerView,
                            myNoteData: List<MyNote>) {
    if(recyclerView.adapter is NotesAdapter) {
        val adapter = recyclerView.adapter as NotesAdapter
        adapter.setData(myNoteData)
    }
}

@BindingAdapter("adapter")
fun drawerDataAdapterBinding(recyclerView: RecyclerView,
                             drawerModelData: List<DrawerModel>) {
    if(recyclerView.adapter is DrawerAdapter) {
        val adapter = recyclerView.adapter as DrawerAdapter
        adapter.setData(drawerModelData)
    }
}