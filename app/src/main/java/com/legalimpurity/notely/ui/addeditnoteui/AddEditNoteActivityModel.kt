package com.legalimpurity.notely.ui.addeditnoteui

import android.databinding.ObservableField
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class AddEditNoteActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<AddEditNoteNavigator>(dataManager,schedulerProvider, compositeDisposable) {

    val noteObj = ObservableField<MyNote>()

    var isItNewNote = false

    fun validationDone()
    {
        addNote()
    }

    fun addNote() {
        val myNote = noteObj.get()
        if(isItNewNote) {
            getCompositeDisposable()?.add(getDataManager()
                    .addANewNote(myNote)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe({
                        if (it)
                            getNavigator()?.noteAddedOrUpdated()
                        else
                            getNavigator()?.apiError(Throwable(""))
                    }))
        }
        else
        {
            getCompositeDisposable()?.add(getDataManager()
                    .updateNote(myNote)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe({
                        if (it)
                            getNavigator()?.noteAddedOrUpdated()
                        else
                            getNavigator()?.apiError(Throwable(""))
                    }))
        }
    }
}