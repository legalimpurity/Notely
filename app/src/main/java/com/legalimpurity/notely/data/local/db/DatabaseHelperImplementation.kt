package com.legalimpurity.notely.data.local.db

import com.legalimpurity.notely.data.local.models.local.MyNote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rkhanna on 26/1/18.
 */
@Singleton
class DatabaseHelperImplementation @Inject constructor(private val mAppDatabase: AppDatabase) : DatabaseHelper {

    override fun addANewNote(myNote: MyNote) = Observable.fromCallable {
        mAppDatabase.notesDao().insert(myNote)
        true
    }
}