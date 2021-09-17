package com.example.ulessontest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ulessontest.domains.LiveLessonModel

@Entity(tableName = "livelesson")
data class LiveLesson(
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


fun LiveLesson.asDomainModel(): LiveLessonModel {
    return LiveLessonModel(
        id = this.id,
        topic = this.topic,
        createdAt = this.createdAt,
        expires_at = this.expires_at,
        image_url = this.image_url,
        tutor = this.tutor,
        subject = this.subject,
        start_at = this.start_at,
        status = this.status
    )
}



fun List<LiveLesson>.asDomainModel() : List<LiveLessonModel> {
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