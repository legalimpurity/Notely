package com.legalimpurity.notely.ui.notesui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.databinding.ActivityNotesBinding
import com.legalimpurity.notely.ui.addeditnoteui.openAddEditNoteActivity
import com.legalimpurity.notely.ui.baseui.BaseActivity
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter
import javax.inject.Inject

/**
 * Created by rkhanna on 26/1/18.
 */
class NotesActivity : BaseActivity<ActivityNotesBinding, NotesActivityModel>(), NotesActivityNavigator {

    @Inject
    lateinit var mNotesActivityModel: NotesActivityModel

    @Inject lateinit var mLayoutManager : RecyclerView.LayoutManager

    @Inject lateinit var mItemAnimator : RecyclerView.ItemAnimator

    @Inject lateinit var mNotesAdapter: NotesAdapter

    private var mActivityNotesBinding: ActivityNotesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNotesActivityModel.setNavigator(this)
        mActivityNotesBinding = getViewDataBinding()
        setUpCoursesAdapter()
        subscribeToLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.notes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                openAddEditNoteActivity(this,null,null)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeToLiveData()
    {
        mNotesActivityModel.getNotesLiveData().observe(this, object : Observer<List<MyNote>> {
            override fun onChanged(courses: List<MyNote>?) {
                courses?.let{ mNotesActivityModel.addCoursesToList(it) }
            }
        })
    }

    private fun setUpCoursesAdapter()
    {
        mActivityNotesBinding?.notesRecyclerView?.layoutManager = mLayoutManager
        mActivityNotesBinding?.notesRecyclerView?.itemAnimator = mItemAnimator
        mActivityNotesBinding?.notesRecyclerView?.adapter = mNotesAdapter
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mNotesActivityModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_notes

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

}