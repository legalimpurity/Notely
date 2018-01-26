package com.legalimpurity.notely.ui.viewnoteui

import android.os.Bundle
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.databinding.ActivityViewNoteBinding
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rajatkhanna on 26/01/18.
 */
class ViewNoteActivity : BaseActivity<ActivityViewNoteBinding, ViewNoteViewModel>(), ViewNoteNavigator {

    @Inject
    lateinit var mViewNoteViewModel: ViewNoteViewModel

    private var mActivityViewNoteBinding: ActivityViewNoteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewNoteViewModel.setNavigator(this)
        mActivityViewNoteBinding = getViewDataBinding()
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mViewNoteViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_view_note

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

}