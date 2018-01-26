package com.legalimpurity.notely.ui.baseui

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by rkhanna on 26/1/18.
 */
abstract class BaseRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(pos: Int)
}