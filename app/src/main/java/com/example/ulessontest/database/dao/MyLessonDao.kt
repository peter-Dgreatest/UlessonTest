package com.example.ulessontest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ulessontest.database.entities.MyLesson

@Dao
abstract class MyLessonDao {
    @Query("select * from mylesson")
    abstract fun getMyLessons(): LiveData<List<MyLesson>>


    @Query("delete from mylesson")
    abstract fun clearMyLessons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMyContent(vararg lesson: MyLesson) : List<Long>
}