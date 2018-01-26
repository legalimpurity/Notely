package com.legalimpurity.notely.di

import com.legalimpurity.notely.ui.addviewnoteactivity.AddViewNoteActivity
import com.legalimpurity.notely.ui.addviewnoteactivity.AddViewNoteActivityModule
import com.legalimpurity.notely.ui.notesactivity.NotesActivity
import com.legalimpurity.notely.ui.notesactivity.NotesActivityModule
import com.legalimpurity.notely.ui.splashui.SplashActivity
import com.legalimpurity.notely.ui.splashui.SplashActivityModule
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

    @ContributesAndroidInjector(modules = [AddViewNoteActivityModule::class])
    abstract fun bindAddViewNotesActivity(): AddViewNoteActivity
}