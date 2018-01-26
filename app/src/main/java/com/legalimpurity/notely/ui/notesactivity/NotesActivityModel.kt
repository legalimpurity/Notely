package com.legalimpurity.notely.ui.notesactivity

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class NotesActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<NotesActivityNavigator>(dataManager,schedulerProvider, compositeDisposable) {

}