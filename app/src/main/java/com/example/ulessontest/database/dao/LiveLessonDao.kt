package com.example.ulessontest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ulessontest.database.entities.LiveLesson

@Dao
abstract class LiveLessonDao {

    @Query("select * from livelesson")
    abstract fun getLiveLessons(): LiveData<List<LiveLesson>>


    @Query("delete from livelesson")
    abstract fun clearLiveLessons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLessonContent(vararg lesson: LiveLesson) : List<Long>

}