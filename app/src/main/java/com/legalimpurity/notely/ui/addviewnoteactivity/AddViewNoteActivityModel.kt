package com.legalimpurity.notely.ui.addviewnoteactivity

import android.databinding.ObservableField
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class AddViewNoteActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<AddViewNoteNavigator>(dataManager,schedulerProvider, compositeDisposable) {

    val noteTitle = ObservableField<String>()
    val noteGist = ObservableField<String>()

    fun validationDone()
    {
        addNote()
    }

    fun addNote() {
        val myNote = MyNote()
        myNote.noteTitle = noteTitle.get()
        myNote.noteGist = noteGist.get()
        getCompositeDisposable()?.add(getDataManager()
                .addANewNote(myNote)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({
                    if(it)
                        getNavigator()?.noteAddedOrUpdated()
                    else
                        getNavigator()?.apiError(Throwable(""))
                }))
    }
}