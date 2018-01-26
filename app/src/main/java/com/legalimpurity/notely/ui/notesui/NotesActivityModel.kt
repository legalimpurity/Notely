package com.legalimpurity.notely.ui.notesui

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class NotesActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<NotesActivityNavigator>(dataManager,schedulerProvider, compositeDisposable) {

    init {
        fetchNotes()
    }

    private val notesObservableArrayList = ObservableArrayList<MyNote>()
    private val notesLiveData: MutableLiveData<List<MyNote>> = MutableLiveData()

    private fun fetchNotes() {
        setIsLoading(true)
        getCompositeDisposable()?.add(getDataManager()
                .getLocalNotes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ courseResponse ->
                    notesLiveData.value = courseResponse
                    setIsLoading(false)
                }, { throwable ->
                    setIsLoading(false)
                    getNavigator()?.apiError(throwable)
                }))
    }

    fun getNotesLiveData() = notesLiveData
    fun getNotesObservableArrayList() = notesObservableArrayList

    fun addCoursesToList(courses: List<MyNote>) {
        notesObservableArrayList.clear()
        notesObservableArrayList.addAll(courses)
    }

}