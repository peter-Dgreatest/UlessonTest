package com.example.ulessontest.repository

import android.app.Application
import com.example.msalehstoreapp.network.Network
import com.example.msalehstoreapp.network.helpers.SafeApiCall
import com.example.ulessontest.database.dao.LessonDao
import com.example.ulessontest.domains.asDatabaseModel
import com.example.ulessontest.domains.asDatabasemModel

class LessonRepository(private val database : LessonDao, val application: Application) : SafeApiCall {


    suspend fun getLiveLesson() {
        safeApiCall {
            val info = Network(application.applicationContext).mnetworks.getLiveLessons().await()
            if (info.asDatabaseModel().isNotEmpty()) {
                database.insertLessonContent(*info.asDatabaseModel())
            }
        }
    }

    val livelessons by lazy{ database.getLiveLessons()}
    val mylessons by lazy{ database.getMyLessons()}


    suspend fun getLessons() {
        safeApiCall {
            val info = Network(application.applicationContext).mnetworks.getMyLessons().await()
            if (info.asDatabaseModel().isNotEmpty()) {
                database.insertMyContent(*info.asDatabasemModel())
            }
        }
    }
}