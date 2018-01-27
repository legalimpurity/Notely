package com.legalimpurity.notely.ui.notesui

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.data.local.models.local.MyNote
import com.legalimpurity.notely.data.local.models.others.DrawerModel
import com.legalimpurity.notely.ui.baseui.BaseViewModel
import com.legalimpurity.notely.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rkhanna on 26/1/18.
 */
class NotesActivityModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BaseViewModel<NotesActivityNavigator>(dataManager,schedulerProvider, compositeDisposable) {

    var drawerOpen = false

    private val notesObservableArrayList = ObservableArrayList<MyNote>()
    private val notesLiveData: MutableLiveData<List<MyNote>> = MutableLiveData()

    private val drawerObservableArrayList = ObservableArrayList<DrawerModel>()
    val drawerLiveData: MutableLiveData<List<DrawerModel>> = MutableLiveData()

    fun fetchNotes(heartedFilterStatus:Boolean, favdFilterStatus:Boolean) {
        setIsLoading(true)
        getCompositeDisposable()?.add(getDataManager()
                .getLocalNotes(heartedFilterStatus,favdFilterStatus)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ notesResponse ->
                    notesLiveData.value = notesResponse
                    setIsLoading(false)
                }, { throwable ->
                    setIsLoading(false)
                    getNavigator()?.apiError(throwable)
                }))
    }

    fun getNotesLiveData() = notesLiveData
    fun getNotesObservableArrayList() = notesObservableArrayList
    fun getDrawerObservableArrayList() = drawerObservableArrayList

    fun addNotesToList(notes: List<MyNote>) {
        notesObservableArrayList.clear()
        notesObservableArrayList.addAll(notes)
    }

    fun addDrawerItemsToList(drawerItems: List<DrawerModel>) {
        drawerObservableArrayList.clear()
        drawerObservableArrayList.addAll(drawerItems)
    }

    fun updateMyNote(myNote: MyNote)
    {
        getCompositeDisposable()?.add(getDataManager()
                .updateNote(myNote)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ insertSuccessfull ->
                    onApplyClick()
//                    if(insertSuccessfull)
                }, { throwable ->
//                    setIsLoading(false)
                    getNavigator()?.apiError(throwable)
                }))
    }

    fun onApplyClick()
    {
        drawerLiveData.value?.let {
            getDataManager().setHeartedFilterStatus(it[0].selected)
            getDataManager().setFavdFilterStatus(it[1].selected)
            fetchNotes(it[0].selected,it[1].selected)
            getNavigator()?.clearFilterChangeStatusAndCloseDrawer()

        }
    }

}