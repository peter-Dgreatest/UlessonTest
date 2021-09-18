package com.example.ulessontest.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ulessontest.R
import com.example.ulessontest.adapters.LessonClickListener
import com.example.ulessontest.adapters.LessonsAdapter
import com.example.ulessontest.databinding.ActivityMyLessonBinding
import com.example.ulessontest.ui.viewmodels.MyLessonViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_my_lesson.*

class MyLessonActivity : AppCompatActivity() {


    private lateinit var myLessonViewModel : MyLessonViewModel
    private lateinit var lessonAdapter: LessonsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_lesson)

        // calling the action bar
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        

        actionBar?.title = "My Lessons"

        val binding : ActivityMyLessonBinding = DataBindingUtil.setContentView<ActivityMyLessonBinding>(this,R.layout.activity_my_lesson)

        myLessonViewModel = ViewModelProviders.of(this).get(MyLessonViewModel::class.java)

        lessonAdapter= LessonsAdapter(LessonClickListener{
                lessonId, lessonTitle ->
            Snackbar.make(binding.lessonRcyView,lessonTitle, Snackbar.LENGTH_LONG).show()

        },"mylesson")

        binding.lessonRcyView.adapter= lessonAdapter

        myLessonViewModel.lessonsn.observe(this, Observer {
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
                (binding.lessonRcyView.adapter as LessonsAdapter).filter.filter(spinner.selectedItem.toString())
                // return true
            }

        }


        binding.viewmodel = myLessonViewModel

    }


}