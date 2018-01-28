package com.legalimpurity.notely.ui.notesui.notesadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.databinding.ItemNoteBinding
import com.legalimpurity.notely.ui.baseui.BaseRecyclerViewHolder

/**
 * Created by rkhanna on 26/1/18.
 */
class NotesAdapter: RecyclerView.Adapter<BaseRecyclerViewHolder>()
{
    private var noteList: MutableList<MyNote> = ArrayList()
    private var notesAdapterListener: NotesAdapterListener? = null
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder?, position: Int) {
        holder?.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerViewHolder {
        var mItemNoteBinding: ItemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        return CourseViewHolder(mItemNoteBinding)
    }

    fun actualRemove(pos:Int)
    {
        noteList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun removeAt(pos:Int)
    {
        notesAdapterListener?.onSwipped(noteList[pos],pos)
    }

    override fun getItemCount(): Int = noteList.size

    fun setData(repoList: List<MyNote>?) {

        repoList?.let {
            noteList.clear()
            noteList.addAll(repoList)
            notifyDataSetChanged()
        }
    }

    fun setListener(notesAdapterListener: NotesAdapterListener)
    {
        this.notesAdapterListener = notesAdapterListener
    }
    // ViewHolder Class
    inner class CourseViewHolder(private val mItemNoteBinding: ItemNoteBinding): BaseRecyclerViewHolder(mItemNoteBinding.root)
    {

        override fun onBind(pos: Int) {
            mItemNoteBinding.myNote = noteList[pos]
            mItemNoteBinding.listener = notesAdapterListener
            mItemNoteBinding.executePendingBindings()
        }
    }

    fun addItems(myNotes: List<MyNote>) {
        noteList.addAll(myNotes)
        notifyDataSetChanged()
    }

    fun clearItems() {
        noteList.clear()
    }
}