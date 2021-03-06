package com.legalimpurity.notely

import android.app.Activity
import android.app.Application
import com.legalimpurity.notely.di.AppComponent
import com.legalimpurity.notely.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

/**
 * Created by rkhanna on 25/1/18.
 */
class NotelyApp: Application(), HasActivityInjector
{
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var mCalligraphyConfig: CalligraphyConfig

    // Initialized by Lambda the first time it is supposed to be used.
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

}