package com.example.ulessontest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ulessontest.databinding.LiveLessonsLoaderBinding
import com.example.ulessontest.databinding.MyLessonsLoaderBinding
import com.example.ulessontest.domains.LiveLessonModel

class LessonsAdapter(val clickListener: LessonClickListener, val pageType: String):
    ListAdapter<LiveLessonModel, RecyclerView.ViewHolder>(LessonDiffCallBack()),Filterable {

    var mListRef: List<LiveLessonModel>? = null
    var mFilteredList: List<LiveLessonModel>? = null

//    fun modifyList(list : List<LiveLessonModel>) {
//        unfilteredlist = list
//        submitList(list)
//    }
//
//    fun filter(query: CharSequence?) {
//        val list = mutableListOf<LiveLessonModel>()
//
//        // perform the data filtering
//        if(!query.isNullOrEmpty()) {
//            list.addAll(unfilteredlist.filter {
//                it.subject.equals(query)
//            })
//        } else {
//            list.addAll(unfilteredlist)
//        }
//
//        submitList(list)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (pageType) {
            "livelesson" ->
                return LiveLessonModelViewHolder.from(parent)
            else ->
                return MyLessonViewHolder.from(parent)
        }
    }


//    override fun getItemCount() = data.size

    class LiveLessonModelViewHolder private constructor(val binding: LiveLessonsLoaderBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {


        fun bind(item: LiveLessonModel, clickListener: LessonClickListener) {
            binding.lesson = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): LiveLessonModelViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LiveLessonsLoaderBinding.inflate(layoutInflater, parent, false)
                return LiveLessonModelViewHolder(binding)
            }
        }
    }

    class MyLessonViewHolder private constructor(val binding: MyLessonsLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: LiveLessonModel, clickListener: LessonClickListener) {
            binding.lesson = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyLessonViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MyLessonsLoaderBinding.inflate(layoutInflater, parent, false)
                return MyLessonViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = getItem(position)
        lateinit var vHolder: Any
        when (pageType) {
            "livelesson" -> {
                vHolder = (holder as LiveLessonModelViewHolder)!!
                vHolder.bind(item, clickListener)
            }
            else -> {
                vHolder = (holder as? MyLessonViewHolder)!!
                vHolder.bind(item, clickListener)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                val list = mutableListOf<LiveLessonModel>()
                if(!charString.isNullOrEmpty() || !charString.contains("All Subjects")) {
                    mListRef?.let {
                        list.addAll(it.filter {
                            it.subject.contains(charString)
                        })
                    }
                } else {
                    mListRef?.let { list.addAll(it) }
                }
                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mFilteredList = filterResults.values as List<LiveLessonModel>?

                submitList(mFilteredList)
            }
        }
    }

}

class  LessonDiffCallBack:DiffUtil.ItemCallback<LiveLessonModel>(){
    override fun areItemsTheSame(oldItem: LiveLessonModel, newItem: LiveLessonModel): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: LiveLessonModel, newItem: LiveLessonModel): Boolean {
        return oldItem==newItem
    }

}

class LessonClickListener(val clickListener: (lessonId: String, lessonName: String) -> Unit){
    fun onClick(lesson: LiveLessonModel) = clickListener(lesson.id, lesson.topic)
}