package com.legalimpurity.notely.ui.splashui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
@Module
class SplashActivityModule
{
    @Provides
    fun provideSplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : SplashViewModel = SplashViewModel(dataManager, schedulerProvider, compositeDisposable)
}