package com.example.ulessontest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ulessontest.database.entities.LiveLesson
import com.example.ulessontest.database.entities.MyLesson

@Dao
abstract class LessonDao {

    @Query("select * from livelesson")
    abstract fun getLiveLessons(): LiveData<List<LiveLesson>>


    @Query("delete from livelesson")
    abstract fun clearLiveLessons()


    @Query("select * from mylesson")
    abstract fun getMyLessons(): LiveData<List<MyLesson>>


    @Query("delete from mylesson")
    abstract fun clearMyLessons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLessonContent(vararg lesson: LiveLesson) : List<Long>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMyContent(vararg lesson: MyLesson) : List<Long>
}