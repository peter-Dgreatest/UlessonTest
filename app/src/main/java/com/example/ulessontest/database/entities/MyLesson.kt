package com.example.ulessontest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ulessontest.domains.LiveLessonModel

@Entity(tableName = "mylesson")
data class MyLesson(
    @PrimaryKey
    val id : String,
    val tutor : String,
    val subject : String,
    val image_url : String,
    val status : String,
    val topic: String,
    val createdAt:String,
    val start_at:String,
    val expires_at:String
)


fun List<MyLesson>.asDomainModel(): List<LiveLessonModel> {
    return this.map {
        LiveLessonModel(
            id = it.id,
            topic = it.topic,
            createdAt = it.createdAt,
            expires_at = it.expires_at,
            image_url = it.image_url,
            tutor = it.tutor,
            subject = it.subject,
            start_at = it.start_at,
            status = it.status
        )
    }
}