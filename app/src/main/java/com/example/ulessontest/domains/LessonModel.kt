package com.example.ulessontest.domains

import com.example.ulessontest.database.entities.LiveLesson
import com.example.ulessontest.database.entities.MyLesson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LiveLessonModel(
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

@JsonClass(generateAdapter = true)
data class NetworkLessonContainer(val data: List<NetworkLesson>)


/**
 *
 */
@JsonClass(generateAdapter = true)
data class NetworkLesson(
    val id : String,
    @field:Json(name = "tutor")
    val tutor : Person,
    @field:Json(name = "subject")
    val subject : Subject,
    val image_url : String,
    val status : String,
    val topic: String,
    val createdAt:String,
    val start_at:String,
    val expires_at:String)

/**
 * Convert Network results to database objects
 */
fun NetworkLessonContainer.asDatabasemModel(): Array<MyLesson> {
    return data.map {
        MyLesson(
            id = it.id,
            topic = it.topic,
            createdAt = it.createdAt,
            expires_at = it.expires_at,
            image_url = it.image_url,
            tutor = it.tutor.firstname,
        subject = it.subject.name,
        start_at = it.start_at,
        status = it.status)
    }.toTypedArray()
}

fun NetworkLessonContainer.asDatabaseModel(): Array<LiveLesson> {
    return data.map {
        LiveLesson ( id = it.id,
            topic = it.topic,
            createdAt = it.createdAt,
            expires_at = it.expires_at,
            image_url = it.image_url,
            tutor = it.tutor.firstname,
            subject = it.subject.name,
            start_at = it.start_at,
            status = it.status)
    }.toTypedArray()
}
