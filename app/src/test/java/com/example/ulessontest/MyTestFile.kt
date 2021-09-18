package com.example.ulessontest

import com.example.ulessontest.database.AppDatabase
import com.example.ulessontest.database.entities.LiveLesson
import com.example.ulessontest.repository.LessonRepository
import com.example.ulessontest.ui.LiveLessonActivity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MyTestFile{


    lateinit var database : AppDatabase
    lateinit var lessonRepository:LessonRepository



    @Before
    fun setUp() {
        var liveLessonActivity = LiveLessonActivity()
        database = AppDatabase.getInstance(liveLessonActivity.applicationContext)
        lessonRepository = LessonRepository(database = database.liveLessonDao, application = liveLessonActivity.application)
    }

    @Test
    fun testDbaseInsert(){
        var livelesson= LiveLesson(
            id = "less1",
            topic = "less1",
            subject = "subject1",
            status = "live",
            tutor = "James Bond",
            start_at = "2021-09-02T06:58:51.000Z",
            expires_at = "2021-09-02T08:58:51.000Z",
            image_url = "https://source.unsplash.com/random/800x200",
            createdAt = "2021-09-02T21:16:36.847Z"
        )


        // first clear database
        database.liveLessonDao.clearLiveLessons()

        database.liveLessonDao.insertLessonContent(livelesson)

        var fetchedData = database.liveLessonDao.getLiveLessons()

        Assert.assertEquals(livelesson, fetchedData.value)

    }

    @Test
    fun fetchDataOnline() = runBlocking{
        // first clear database
        database.liveLessonDao.clearLiveLessons()
        lessonRepository.getLiveLesson()

        // check if anything was saved assuming api return results

        var fetchedData = database.liveLessonDao.getLiveLessons()
        Assert.assertTrue(fetchedData.value!!.get(0).topic != null)
    }
}