package com.legalimpurity.notely.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.legalimpurity.notely.data.DataManager
import com.legalimpurity.notely.data.DataManagerImplementation
import com.legalimpurity.notely.data.local.db.AppDatabase
import com.legalimpurity.notely.data.local.db.DatabaseHelper
import com.legalimpurity.notely.data.local.db.DatabaseHelperImplementation
import com.legalimpurity.notely.data.local.prefs.PreferenceHelper
import com.legalimpurity.notely.data.local.prefs.PreferenceHelperImplementation
import com.legalimpurity.notely.di.DatabaseInfo
import com.legalimpurity.notely.di.PreferenceInfo
import com.legalimpurity.notely.util.DB_NAME
import com.legalimpurity.notely.util.PREF_NAME
import com.legalimpurity.notely.util.rx.SchedulerProvider
import com.legalimpurity.notely.util.rx.SchedulerProviderImplementation
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by rkhanna on 25/1/18.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataManager(appDataManager: DataManagerImplementation): DataManager = appDataManager

    @Singleton
    @Provides
    fun provideScheduleProvider(): SchedulerProvider = SchedulerProviderImplementation()

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app

    @Singleton
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    fun providePreferenceHelper(appPreferencesHelper: PreferenceHelperImplementation): PreferenceHelper = appPreferencesHelper

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: DatabaseHelperImplementation): DatabaseHelper {
        return appDbHelper
    }

    @PreferenceInfo
    @Provides
    fun providePreferenceName(): String = PREF_NAME

    @DatabaseInfo
    @Provides
    fun provideDatabaseName(): String = DB_NAME
}