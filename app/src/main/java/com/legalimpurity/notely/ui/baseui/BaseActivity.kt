package com.legalimpurity.notely.ui.baseui

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import dagger.android.AndroidInjection

/**
 * Created by rkhanna on 25/1/18.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<out BaseNavigator>>: AppCompatActivity(), BaseFragment.Callbacks {

    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    override fun onCreate(savedInstanceState : Bundle?)
    {
        performDependencyInjection()
        // This is to be done so that the fragment in the view does now calls its dependency injection before, or at least that why i think this is done in such a manner.
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding()
    {
        mViewModel = getViewModel()
        mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId())
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection()
    {
        AndroidInjection.inject(this)
    }

    fun getViewDataBinding() : T = mViewDataBinding


    //Functions to be implemented by Activities
    abstract fun getViewModel(): V
    abstract fun getBindingVariable() : Int
    @LayoutRes
    abstract fun getLayoutId() : Int


    // Fragment Functions
    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // snackbarActionClicker is -1 in case of no action
    fun showMsg(errorString: Int, actionString: Int, snackbarActionClicker: (actionButView: View) -> Unit)
    {
        var sk = Snackbar.make(mViewDataBinding.root,errorString, Snackbar.LENGTH_LONG)
        if(actionString!=-1) {
            sk.setAction(actionString, { actionButView ->
                snackbarActionClicker.invoke(actionButView)
                sk.dismiss()
            }
            )
        }
        sk.show()
    }
}