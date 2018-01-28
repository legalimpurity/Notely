package com.legalimpurity.notely.di

import com.legalimpurity.notely.ui.addeditnoteui.AddEditNoteActivity
import com.legalimpurity.notely.ui.addeditnoteui.AddEditNoteActivityModule
import com.legalimpurity.notely.ui.notesui.NotesActivity
import com.legalimpurity.notely.ui.notesui.NotesActivityModule
import com.legalimpurity.notely.ui.splashui.SplashActivity
import com.legalimpurity.notely.ui.splashui.SplashActivityModule
import com.legalimpurity.notely.ui.viewnoteui.ViewNoteActivity
import com.legalimpurity.notely.ui.viewnoteui.ViewNoteModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rkhanna on 25/1/18.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [NotesActivityModule::class])
    abstract fun bindNotesActivity(): NotesActivity

    @ContributesAndroidInjector(modules = [AddEditNoteActivityModule::class])
    abstract fun bindAddEditNotesActivity(): AddEditNoteActivity

    @ContributesAndroidInjector(modules = [ViewNoteModule::class])
    abstract fun bindViewNotesActivity(): ViewNoteActivity
}