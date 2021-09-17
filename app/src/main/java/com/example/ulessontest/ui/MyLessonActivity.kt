package com.example.ulessontest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ulessontest.R
import com.example.ulessontest.adapters.LessonClickListener
import com.example.ulessontest.adapters.LessonsAdapter
import com.example.ulessontest.database.entities.LiveLesson
import com.example.ulessontest.databinding.ActivityLiveLessonBinding
import com.example.ulessontest.databinding.ActivityMyLessonBinding
import com.example.ulessontest.domains.LiveLessonModel
import com.example.ulessontest.ui.viewmodels.LiveLessonViewModel
import com.example.ulessontest.ui.viewmodels.MyLessonViewModel
import com.google.android.material.snackbar.Snackbar

class MyLessonActivity : AppCompatActivity() {


    lateinit var liveLessonViewModel : MyLessonViewModel
    lateinit var lessonAdapter: LessonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_lesson)

        // calling the action bar
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        actionBar?.title = "My Lessons"

        val binding : ActivityMyLessonBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_lesson)

        liveLessonViewModel = ViewModelProviders.of(this).get(MyLessonViewModel::class.java)

        lessonAdapter= LessonsAdapter(LessonClickListener{
                lessonId, lessonTitle ->
            Snackbar.make(binding.lessonRcyView,lessonTitle, Snackbar.LENGTH_LONG).show()

        },"mylesson")

        binding.lessonRcyView.adapter= lessonAdapter

        liveLessonViewModel.lessons.observe(this, Observer {
            it?.let {
                lessonAdapter.submitList(it as List<LiveLessonModel>)
            }
        })




        binding.viewmodel = liveLessonViewModel

    }


}