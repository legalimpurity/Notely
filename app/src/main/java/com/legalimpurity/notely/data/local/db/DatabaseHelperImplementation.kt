package com.legalimpurity.notely.data.local.db

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rkhanna on 26/1/18.
 */
@Singleton
class DatabaseHelperImplementation @Inject constructor(private val mAppDatabase: AppDatabase) : DatabaseHelper {

}