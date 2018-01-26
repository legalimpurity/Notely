package com.legalimpurity.notely.ui.viewnoteui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rajatkhanna on 26/01/18.
 */
@Module
class ViewNoteModule
{
    @Provides
    fun provideViewNoteViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) = ViewNoteViewModel(dataManager, schedulerProvider, compositeDisposable)
}