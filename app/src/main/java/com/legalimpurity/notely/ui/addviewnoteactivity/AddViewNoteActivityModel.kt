package com.legalimpurity.notely.ui.addviewnoteactivity

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class AddViewNoteActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<AddViewNoteNavigator>(dataManager,schedulerProvider, compositeDisposable) {

}