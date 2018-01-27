package com.legalimpurity.notely.ui.addeditnoteui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.text.TextUtils
import android.util.Pair
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.databinding.ActivityAddViewNoteBinding
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rkhanna on 26/1/18.
 */

private val MY_NOTE_ACTIVITY_OBJECT = "MY_NOTE_ACTIVITY_OBJECT"
fun openAddEditNoteActivity(activity: Activity, myNote: MyNote?)
{
    val intent = Intent()
    intent.setClass(activity, AddEditNoteActivity::class.java)
    intent.putExtra(MY_NOTE_ACTIVITY_OBJECT,myNote)
    activity.startActivity(intent)
}

class AddEditNoteActivity : BaseActivity<ActivityAddViewNoteBinding, AddEditNoteActivityModel>(), AddEditNoteNavigator {

    @Inject
    lateinit var mAddEditNoteActivityModel: AddEditNoteActivityModel

    private var mActivityAddViewNoteBinding: ActivityAddViewNoteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddEditNoteActivityModel.setNavigator(this)
        mActivityAddViewNoteBinding = getViewDataBinding()
        loadObj()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_edit_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                checkNoteTitle()
                return true
            }
            R.id.action_undo -> {
                // TODO add dialog confirming and exit activity
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadObj()
    {
        if (intent.hasExtra(MY_NOTE_ACTIVITY_OBJECT)) {
            mAddEditNoteActivityModel.noteObj.set(intent.getParcelableExtra(MY_NOTE_ACTIVITY_OBJECT))
        }
        else {
            mAddEditNoteActivityModel.isItNewNote = true
            mAddEditNoteActivityModel.noteObj.set(MyNote("",""))
        }
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mAddEditNoteActivityModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_add_view_note

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

    override fun checkNoteTitle() {
        if(!TextUtils.isEmpty(mAddEditNoteActivityModel.noteObj.get().getNoteTitle()))
            mAddEditNoteActivityModel.validationDone()
    }

    override fun noteAddedOrUpdated() {
        NavUtils.navigateUpFromSameTask(this)
    }
}