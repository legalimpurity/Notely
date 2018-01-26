package com.legalimpurity.notely.ui.splashui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 25/1/18.
 */
class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<SplashActivityNavigator>(dataManager,schedulerProvider, compositeDisposable) {

}