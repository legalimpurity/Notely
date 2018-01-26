package com.legalimpurity.notely.ui.addeditnoteactivity

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.databinding.ActivityAddViewNoteBinding
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rkhanna on 26/1/18.
 */
class AddEditNoteActivity : BaseActivity<ActivityAddViewNoteBinding, AddEditNoteActivityModel>(), AddEditNoteNavigator {

    @Inject
    lateinit var mAddEditNoteActivityModel: AddEditNoteActivityModel

    private var mActivityAddViewNoteBinding: ActivityAddViewNoteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddEditNoteActivityModel.setNavigator(this)
        mActivityAddViewNoteBinding = getViewDataBinding()
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


    // Functions to be implemented by every Activity
    override fun getViewModel() = mAddEditNoteActivityModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_add_view_note

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

    override fun checkNoteTitle() {
        if(!TextUtils.isEmpty(mAddEditNoteActivityModel.noteTitle.get()))
            mAddEditNoteActivityModel.validationDone()
    }

    override fun noteAddedOrUpdated() {
        NavUtils.navigateUpFromSameTask(this)
    }
}