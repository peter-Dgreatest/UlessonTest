package com.example.ulessontest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
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
import kotlinx.android.synthetic.main.activity_live_lesson.*


class LiveLessonActivity : AppCompatActivity() {


    private lateinit var liveLessonViewModel : LiveLessonViewModel
    private lateinit var lessonAdapter: LessonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding : ActivityLiveLessonBinding  = DataBindingUtil.setContentView<ActivityLiveLessonBinding>(this,R.layout.activity_live_lesson)


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
            binding.cardview.visibility = if (it != null){
                if (it?.isEmpty())
                    View.VISIBLE
                else
                    View.GONE
            } else {
                View.VISIBLE
            }
            it?.apply {
                lessonAdapter.mListRef=it
                lessonAdapter.submitList(it)
            }
        })


        binding.spinner.onItemSelectedListener=  object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                (binding.recyView.adapter as LessonsAdapter).filter.filter(spinner.selectedItem.toString())
               // return true
            }

        }
        liveLessonViewModel.navigateToNextActivity.observe(this, Observer {
            if(it){
                startActivity(Intent(this@LiveLessonActivity,MyLessonActivity::class.java))

            }
        })



        binding.leviewmodel = liveLessonViewModel


    }
}