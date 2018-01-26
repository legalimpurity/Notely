package com.legalimpurity.notely.ui.baseui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 25/1/18.
 */
abstract class BaseViewModel<N : BaseNavigator>(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : ViewModel()
{
    private var mNavigator: N? = null

    private var compositeDisposable: CompositeDisposable? = null
    private var schedulerProvider: SchedulerProvider? = null
    private var dataManager: DataManager? = null
    private val mIsLoading = ObservableBoolean(false)

    init {
        this.dataManager = dataManager
        this.schedulerProvider = schedulerProvider
        this.compositeDisposable = compositeDisposable
    }

    fun getCompositeDisposable() = compositeDisposable
    fun getSchedulerProvider() = schedulerProvider!!
    fun getDataManager() = dataManager!!

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    fun getNavigator(): N? = mNavigator

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }
}