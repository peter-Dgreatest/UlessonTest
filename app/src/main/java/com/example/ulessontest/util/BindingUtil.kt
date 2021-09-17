package com.example.ulessontest.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.ulessontest.R
import com.example.ulessontest.database.entities.LiveLesson
import com.example.ulessontest.domains.LiveLessonModel


@BindingAdapter("setImage")
fun ImageView.bindImageWithGlide(src: String){
    Glide.with(this.context).load(src).into(this)
}

/*
* Binding adapter used to hide the cardview once data is available
*/
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, its: List<LiveLessonModel>?) {
    view.visibility = if (its == null){
        View.GONE
    } else {
        if (its?.size!! <1)  View.GONE
        else View.VISIBLE
    }
}

@BindingAdapter("setTime")
fun TextView.setTime(lesson:LiveLessonModel){
    this.text = convertTextToTime(lesson.start_at,lesson.expires_at)
}

@BindingAdapter("bindSpinner")
fun Spinner.bindSpiner(entries: ArrayList<String>){
    val arrayAdapter: ArrayAdapter<String> =
        ArrayAdapter(this.context,
            R.layout.my_selected_item,
            entries?: listOf(""))
    arrayAdapter.setDropDownViewResource(R.layout.my_dropdown_item)
    this.setAdapter(arrayAdapter)

}

@BindingAdapter("bindTextColorAndText")
fun TextView.bindTextColorAndText(text:String){
    val bgColor : Int
    when (text) {
        "Physics" -> {
            bgColor = Color.parseColor("#7B7FDA")
        }
        "Chemistry" -> {
            bgColor = Color.parseColor("#F9AD6D")
        }
        "English" -> {
            bgColor = Color.parseColor("#7B7FDA")
        }
        "Mathematics" -> {
            bgColor = Color.parseColor("#EA7052")
        }
        else -> {
            bgColor = Color.parseColor("#68BC98")
        }
    }
    this.text= text
    this.setTextColor(ColorStateList.valueOf(bgColor))
}

@BindingAdapter("bindButtonStyle")
fun Button.bindButton(text: String) {
    val drawableLeft: Int
    val bgColor: Int
    when (text) {
        "upcoming" -> {
            drawableLeft = (R.drawable.ic_baseline_table_chart_24)
            bgColor = Color.parseColor("#6C6C6C")
        }
        "live" -> {
            drawableLeft = (R.drawable.ic_baseline_adjust_24)
            bgColor = Color.parseColor("#BA0414")
        }
        else -> {
            drawableLeft = (R.drawable.ic_baseline_play_arrow_24)
            bgColor = Color.parseColor("#f9ad6d")
        }
    }

    this.text = text
    this.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, 0, 0, 0)
    this.backgroundTintList = ColorStateList.valueOf(bgColor)
}