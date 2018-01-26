package com.legalimpurity.notely.ui.viewnoteui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rajatkhanna on 26/01/18.
 */
class ViewNoteViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<ViewNoteNavigator>(dataManager,schedulerProvider, compositeDisposable) {

}