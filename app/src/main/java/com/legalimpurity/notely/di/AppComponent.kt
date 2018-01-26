package com.legalimpurity.notely.di

import android.app.Application
import com.legalimpurity.notely.NotelyApp
import com.legalimpurity.notely.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rkhanna on 25/1/18.
 */
@Singleton
@Component(modules = [AppModule::class, BuildersModule::class, AndroidSupportInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppComponent
    }

    fun inject(app: NotelyApp)
}