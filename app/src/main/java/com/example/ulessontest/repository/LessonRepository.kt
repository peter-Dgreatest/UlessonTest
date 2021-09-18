package com.example.ulessontest.repository

import android.app.Application
import com.example.msalehstoreapp.network.Network
import com.example.msalehstoreapp.network.helpers.SafeApiCall
import com.example.ulessontest.database.AppDatabase
import com.example.ulessontest.database.dao.LiveLessonDao
import com.example.ulessontest.database.dao.MyLessonDao
import com.example.ulessontest.domains.asDatabaseModel
import com.example.ulessontest.domains.asDatabasemModel

class LessonRepository(val application: Application) : SafeApiCall {

    private val liveLessonDao : LiveLessonDao = AppDatabase.getInstance(application.applicationContext).liveLessonDao
    private val myLessonDao : MyLessonDao = AppDatabase.getInstance(application.applicationContext).myLessonDao

    suspend fun getLiveLesson() {
        safeApiCall {
            val info = Network(application.applicationContext).mnetworks.getLiveLessons().await()
            if (info.asDatabaseModel().isNotEmpty()) {
                liveLessonDao.insertLessonContent(*info.asDatabaseModel())
            }
        }
    }

    val livelessons by lazy{ liveLessonDao.getLiveLessons()}
    val mylessons by lazy{ myLessonDao.getMyLessons()}


    suspend fun getLessons() {
        safeApiCall {
            val info = Network(application.applicationContext).mnetworks.getMyLessons().await()
            if (info.asDatabaseModel().isNotEmpty()) {
                myLessonDao.insertMyContent(*info.asDatabasemModel())
            }
        }
    }
}