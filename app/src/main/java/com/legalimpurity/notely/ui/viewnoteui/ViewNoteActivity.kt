package com.legalimpurity.notely.ui.viewnoteui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.databinding.ActivityViewNoteBinding
import com.legalimpurity.notely.ui.addeditnoteui.openAddEditNoteActivity
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rajatkhanna on 26/01/18.
 */
private val MY_NOTE_ACTIVITY_OBJECT = "MY_NOTE_ACTIVITY_OBJECT"
fun openViewNoteActivity(activity: Activity, myNote: MyNote?, rootView : View?)
{
    val intent = Intent()
    intent.setClass(activity, ViewNoteActivity::class.java)
    intent.putExtra(MY_NOTE_ACTIVITY_OBJECT,myNote)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && rootView != null) {
        val options: ActivityOptions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(rootView, activity.getString(R.string.transition_myNote_background))
            )
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        activity.startActivity(intent, options.toBundle())
    }
    else
        activity.startActivity(intent)
}
class ViewNoteActivity : BaseActivity<ActivityViewNoteBinding, ViewNoteViewModel>(), ViewNoteNavigator {

    @Inject
    lateinit var mViewNoteViewModel: ViewNoteViewModel

    private var mActivityViewNoteBinding: ActivityViewNoteBinding? = null

    private var mMyNote: MyNote? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewNoteViewModel.setNavigator(this)
        mActivityViewNoteBinding = getViewDataBinding()
        setSupportActionBar(mActivityViewNoteBinding?.toolbar)
        loadObj()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.view_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                openAddEditNoteActivity(this,mMyNote)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadObj()
    {
        if (intent.hasExtra(MY_NOTE_ACTIVITY_OBJECT)) {
            mMyNote = intent.getParcelableExtra(MY_NOTE_ACTIVITY_OBJECT)
            mActivityViewNoteBinding?.myNote = mMyNote
            supportActionBar?.title = mMyNote?.getNoteTitle()
            supportActionBar?.subtitle = mMyNote?.giveFormattedDate()
        }
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mViewNoteViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_view_note

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

}