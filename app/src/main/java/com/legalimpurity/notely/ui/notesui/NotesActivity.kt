package com.legalimpurity.notely.ui.notesui

import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.TranslateAnimation
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.data.local.models.others.DrawerModel
import com.legalimpurity.notely.databinding.ActivityNotesBinding
import com.legalimpurity.notely.ui.addeditnoteui.openAddEditNoteActivity
import com.legalimpurity.notely.ui.baseui.BaseActivity
import com.legalimpurity.notely.ui.notesui.draweradapter.DrawerAdapter
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapterListener
import com.legalimpurity.notely.util.AppLogger
import javax.inject.Inject


/**
 * Created by rkhanna on 26/1/18.
 */
class NotesActivity : BaseActivity<ActivityNotesBinding, NotesActivityModel>(), NotesActivityNavigator {

    @Inject
    lateinit var mNotesActivityModel: NotesActivityModel

    @Inject lateinit var mLayoutManager : RecyclerView.LayoutManager
    @Inject lateinit var mDrawerLayoutManager : RecyclerView.LayoutManager

    @Inject lateinit var mItemAnimator : RecyclerView.ItemAnimator
    @Inject lateinit var mDrawerItemAnimator : RecyclerView.ItemAnimator

    @Inject lateinit var mNotesAdapter: NotesAdapter

    @Inject lateinit var mDrawerAdapter: DrawerAdapter

    private var mActivityNotesBinding: ActivityNotesBinding? = null

    private var lastTranslate = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNotesActivityModel.setNavigator(this)
        mActivityNotesBinding = getViewDataBinding()
        setUpDrawer()
        setDrawerData()
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
            R.id.action_filter -> {
                if(!mNotesActivityModel.drawerOpen)
                    mActivityNotesBinding?.drawerLayout?.openDrawer(Gravity.RIGHT,true)
                else
                    mActivityNotesBinding?.drawerLayout?.closeDrawer(Gravity.RIGHT,true)
                mNotesActivityModel.drawerOpen = !mNotesActivityModel.drawerOpen
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Have to set it up here as cant allow context to enter the model.
    private fun setDrawerData()
    {
        AppLogger.d(mNotesActivityModel.drawerLiveData.value?.size.toString())
        if(mNotesActivityModel.drawerLiveData.value?.size == null) {
            var drawerModel1 = DrawerModel()
            drawerModel1.itemName = getString(R.string.navigation_drawer_hearted)
            drawerModel1.selected = false
            var drawerModel2 = DrawerModel()
            drawerModel2.itemName = getString(R.string.navigation_drawer_favourite)
            drawerModel2.selected = false
            mNotesActivityModel.drawerLiveData.value = arrayListOf(drawerModel1, drawerModel2)
        }
    }

    private fun setUpDrawer()
    {
        val toggle = ActionBarDrawerToggle(this,mActivityNotesBinding?.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        mActivityNotesBinding?.drawerLayout?.addDrawerListener(object : DrawerLayout.DrawerListener
        {
            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                mActivityNotesBinding?.rightDrawer?.width?.let {
                    val moveFactor = it * slideOffset

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        mActivityNotesBinding?.container?.setTranslationX(-moveFactor)
                    } else {
                        val anim = TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f)
                        anim.duration = 0
                        anim.fillAfter = true
                        mActivityNotesBinding?.container?.startAnimation(anim)

                        lastTranslate = moveFactor
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })

        mActivityNotesBinding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        mActivityNotesBinding?.rightDrawer?.adapter = mDrawerAdapter
        mActivityNotesBinding?.rightDrawer?.layoutManager = mDrawerLayoutManager
        mActivityNotesBinding?.rightDrawer?.itemAnimator = mDrawerItemAnimator
    }

    private fun subscribeToLiveData()
    {
        mNotesActivityModel.getNotesLiveData().observe(this, Observer<List<MyNote>> { myNotes -> myNotes?.let{ mNotesActivityModel.addNotesToList(it) } })
        mNotesActivityModel.drawerLiveData.observe(this, Observer<List<DrawerModel>> { drawerItems -> drawerItems?.let{ mNotesActivityModel.addDrawerItemsToList(it) } })
    }

    private fun setUpCoursesAdapter()
    {
        mActivityNotesBinding?.notesRecyclerView?.layoutManager = mLayoutManager
        mActivityNotesBinding?.notesRecyclerView?.itemAnimator = mItemAnimator
        mActivityNotesBinding?.notesRecyclerView?.adapter = mNotesAdapter
        mNotesAdapter.setListener(object : NotesAdapterListener{
            override fun onClick(myNote: MyNote, view: View) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFavd(myNote: MyNote, view: View) {
                myNote.fav = !myNote.fav
                mNotesActivityModel.updateMyNote(myNote)
            }

            override fun onHearted(myNote: MyNote, view: View) {
                myNote.hearted = !myNote.hearted
                mNotesActivityModel.updateMyNote(myNote)
            }

        })
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mNotesActivityModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_notes

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

    override fun refreshAdapter() {
        mNotesAdapter.notifyDataSetChanged()
    }

}