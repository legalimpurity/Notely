package com.legalimpurity.notely.ui.baseui

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

/**
 * Created by rkhanna on 25/1/18.
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<out BaseNavigator>> : Fragment() {

    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel : V

    private var mBaseActivity:BaseActivity<*,*>? = null
    private var mRootView: View? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        mRootView = mViewDataBinding.root
        return mRootView as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(),mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseActivity<*,*>) {
            var baseActivity: BaseActivity<*,*> = context
            mBaseActivity = baseActivity
            baseActivity.onFragmentAttached()
        }
    }

    override fun onDetach() {
        mBaseActivity = null
        super.onDetach()
    }

    private fun performDependencyInjection()
    {
        AndroidSupportInjection.inject(this)
    }

    fun getViewDataBinding() : T = mViewDataBinding

    abstract fun getViewModel() :V
    abstract fun getBindingVariable() : Int
    @LayoutRes
    abstract fun getLayoutId() : Int

    // Fragments interface

    interface Callbacks
    {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}