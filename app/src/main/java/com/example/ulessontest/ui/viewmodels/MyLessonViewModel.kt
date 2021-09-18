package com.example.ulessontest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.ulessontest.database.AppDatabase
import com.example.ulessontest.database.entities.asDomainModel
import com.example.ulessontest.repository.LessonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MyLessonViewModel(application: Application) : AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application.applicationContext).lessonDao
    private val lessonRepository = LessonRepository(database,application)
    private val lessContentk = lessonRepository.mylessons

    val lessonsn = Transformations.map(lessContentk) {
        lessContentk.value?.asDomainModel()
    }

    private val viewModelJob = SupervisorJob()

    val subjects  = ArrayList<String>()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        subjects.add(0, "All Subjects")
        subjects.add( "Mathematics")
        subjects.add( "English")
        subjects.add( "Chemistry")
        subjects.add( "Biology")
        subjects.add( "Physics")
        viewModelScope.launch {
            lessonRepository.getLessons()
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}


