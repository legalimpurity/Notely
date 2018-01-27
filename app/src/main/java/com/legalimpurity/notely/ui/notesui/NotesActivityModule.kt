package com.legalimpurity.notely.ui.notesui

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.ui.notesui.draweradapter.DrawerAdapter
import com.legalimpurity.notely.ui.notesui.notesadapter.NotesAdapter
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

    @Provides
    fun provideCoursesLayoutManager(notesActivity: NotesActivity) : RecyclerView.LayoutManager
    {
        val l = LinearLayoutManager(notesActivity)
        l.orientation = LinearLayoutManager.VERTICAL
        return l
    }

    @Provides
    fun provideCoursesItemAnimator(): RecyclerView.ItemAnimator = DefaultItemAnimator()

    @Provides
    fun provideNotesAdapter() = NotesAdapter()

    @Provides
    fun provideDrawerAdapter() = DrawerAdapter()

}