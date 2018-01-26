package com.legalimpurity.notely.ui.splashui

import android.os.Bundle
import com.legalimpurity.notely.BR
import com.legalimpurity.notely.R
import com.legalimpurity.notely.databinding.ActivitySplashBinding
import com.legalimpurity.notely.ui.baseui.BaseActivity
import javax.inject.Inject

/**
 * Created by rkhanna on 25/1/18.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashActivityNavigator {

    @Inject lateinit var mSplashViewModel: SplashViewModel

    private var mActivitySplashBinding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel.setNavigator(this)
        mActivitySplashBinding = getViewDataBinding()
    }

    // Functions to be implemented by every Activity
    override fun getViewModel(): SplashViewModel = mSplashViewModel

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_splash

    // Navigator Functions
    override fun apiError(throwable: Throwable) {
    }

}