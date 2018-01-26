package com.legalimpurity.notely.ui.addviewnoteactivity

import android.os.Bundle
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.databinding.ActivityAddViewNoteBinding
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rkhanna on 26/1/18.
 */
class AddViewNoteActivity : BaseActivity<ActivityAddViewNoteBinding, AddViewNoteActivityModel>(), AddViewNoteNavigator {

    @Inject
    lateinit var mAddViewNoteActivityModel: AddViewNoteActivityModel

    private var mActivityAddViewNoteBinding: ActivityAddViewNoteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddViewNoteActivityModel.setNavigator(this)
        mActivityAddViewNoteBinding = getViewDataBinding()
    }

    // Functions to be implemented by every Activity
    override fun getViewModel() = mAddViewNoteActivityModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_add_view_note

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

}