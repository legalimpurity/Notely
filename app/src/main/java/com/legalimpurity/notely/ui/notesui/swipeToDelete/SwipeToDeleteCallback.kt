package com.legalimpurity.notely.ui.notesui.swipeToDelete

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.legalimpurity.notely.R
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter

/**
 * Created by rajatkhanna on 28/01/18.
 */
class SwipeToDeleteCallback(context: Context,val notesAdapter: NotesAdapter) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
{
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        viewHolder?.let { notesAdapter.removeAt(it.adapterPosition) }
    }

    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_32dp)
    private val background = ColorDrawable()
    private val backgroundColor = context.resources.getColor(R.color.delete_color)

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        // Draw the red delete background
        background.color = backgroundColor
        background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
        background.draw(c)

        // Calculate position of delete icon
        deleteIcon?.let {
            val intrinsicWidth = it.intrinsicWidth
            val intrinsicHeight = it.intrinsicHeight
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight
            it.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            it.draw(c)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}