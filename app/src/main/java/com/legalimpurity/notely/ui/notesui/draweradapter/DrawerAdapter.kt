package com.legalimpurity.notely.ui.notesui.draweradapter

import android.widget.TextView
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.legalimpurity.notely.R


/**
 * Created by rkhanna on 27/1/18.
 */
class DrawerAdapter(val ctx:Context, val layoutResourceId:Int) : ArrayAdapter<String>(ctx,layoutResourceId)
{
    internal var filterArray = arrayOf(ctx.getString(R.string.navigation_drawer_hearted),ctx.getString(R.string.navigation_drawer_favourite))

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var row: View? = convertView
        var holder: ItemHolder? = null

        if (row == null) {
            val inflater = (context as Activity).layoutInflater
            row = inflater.inflate(layoutResourceId, parent, false)

            holder = ItemHolder()
        }
        holder?.txtName = row?.findViewById(R.id.title)

        val filterItem = filterArray[position]
        holder?.txtName?.setText(filterItem)
        return row!!
    }

    class ItemHolder {
        internal var txtName: TextView? = null
    }
}