package com.legalimpurity.notely.ui.notesui

import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
@Module
class NotesActivityModule
{
    @Provides
    fun provideNotesActivityViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) = NotesActivityModel(dataManager, schedulerProvider, compositeDisposable)
}