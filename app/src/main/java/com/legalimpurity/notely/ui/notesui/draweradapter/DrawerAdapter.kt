package com.legalimpurity.notely.ui.notesui.draweradapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.legalimpurity.notely.data.local.models.others.DrawerModel
import com.legalimpurity.notely.databinding.ItemDrawerBinding
import com.legalimpurity.notely.ui.baseui.BaseRecyclerViewHolder

/**
 * Created by rkhanna on 27/1/18.
 */
class DrawerAdapter: RecyclerView.Adapter<BaseRecyclerViewHolder>()
{
    private var listener:DrawerAdapterListener? = null
    var isFilterModified = false

    init {
        listener = object: DrawerAdapterListener{
            override fun onClick(drawerItem: DrawerModel) {
                isFilterModified = true
                drawerItem.selected = !drawerItem.selected
                notifyDataSetChanged()
            }
        }
    }

    private var drawerItemList: MutableList<DrawerModel> = ArrayList()
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder?, position: Int) {
        holder?.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseRecyclerViewHolder {
        var mItemDrawerBinding: ItemDrawerBinding = ItemDrawerBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        return CourseViewHolder(mItemDrawerBinding)
    }

    override fun getItemCount(): Int = drawerItemList.size

    fun setData(repoList: List<DrawerModel>?) {

        repoList?.let {
            drawerItemList.clear()
            drawerItemList.addAll(repoList)
            notifyDataSetChanged()
        }
    }

    // ViewHolder Class
    inner class CourseViewHolder(private val mItemDrawerBinding: ItemDrawerBinding): BaseRecyclerViewHolder(mItemDrawerBinding.root)
    {

        override fun onBind(pos: Int) {
            mItemDrawerBinding.draweritem = drawerItemList[pos]
            mItemDrawerBinding.listener = listener
            mItemDrawerBinding.executePendingBindings()
        }
    }

    fun addItems(myNotes: List<DrawerModel>) {
        drawerItemList.addAll(myNotes)
        notifyDataSetChanged()
    }

    fun clearItems() {
        drawerItemList.clear()
    }
}