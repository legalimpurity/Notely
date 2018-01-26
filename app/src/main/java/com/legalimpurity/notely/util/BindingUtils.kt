package com.legalimpurity.notely.util

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter

/**
 * Created by rkhanna on 26/1/18.
 */
@BindingAdapter("adapter")
fun notesDataAdapterBinding(recyclerView: RecyclerView,
                                timeTableData: List<MyNote>) {
    if(recyclerView.adapter is NotesAdapter) {
        val adapter = recyclerView.adapter as NotesAdapter
        adapter.setData(timeTableData)
    }
}