package com.legalimpurity.notely.ui.addeditnoteui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
@Module
class AddEditNoteActivityModule
{
    @Provides
    fun provideAddEditNotesActivityViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) = AddEditNoteActivityModel(dataManager, schedulerProvider, compositeDisposable)
}