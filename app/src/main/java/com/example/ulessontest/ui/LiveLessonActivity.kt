package com.example.ulessontest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.ulessontest.R
import com.example.ulessontest.adapters.LessonClickListener
import com.example.ulessontest.adapters.LessonsAdapter
import com.example.ulessontest.databinding.ActivityLiveLessonBinding
import com.example.ulessontest.ui.viewmodels.LiveLessonViewModel
import com.google.android.material.snackbar.Snackbar


class LiveLessonActivity : AppCompatActivity() {


    lateinit var liveLessonViewModel : LiveLessonViewModel
    lateinit var lessonAdapter: LessonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding : ActivityLiveLessonBinding  = DataBindingUtil.setContentView(this,R.layout.activity_live_lesson)


        // calling the action bar
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        actionBar?.title = "Live Lessons"

        liveLessonViewModel = ViewModelProviders.of(this).get(LiveLessonViewModel::class.java)

        lessonAdapter= LessonsAdapter(LessonClickListener{
            lessonId, lessonTitle ->
                Snackbar.make(binding.floatingActionButton,lessonTitle,Snackbar.LENGTH_LONG).show()

        },"livelesson")

        binding.recyView.adapter= lessonAdapter

        liveLessonViewModel.lessons.observe(this, Observer {
            it?.let {
                lessonAdapter.submitList(it)
            }
        })

        liveLessonViewModel.navigateToNextActivity.observe(this, Observer {
            if(it){
                startActivity(Intent(this@LiveLessonActivity,MyLessonActivity::class.java))

            }
        })



        binding.leviewmodel = liveLessonViewModel


    }
}