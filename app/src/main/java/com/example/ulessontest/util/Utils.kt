package com.example.ulessontest.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun convertTextToTime(time: String, timeEnd: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    var returnString:String=""
    var stimeString = time.substring(0, time.lastIndexOf('.'))
    var etimeString = timeEnd.substring(0, time.lastIndexOf('.'))
    input.timeZone = TimeZone.getTimeZone("WAT")


    val strDate = input.parse(stimeString)
    val estrDate = input.parse(etimeString)
    val dateNow = Date()

    if (dateNow.after(strDate)) {
        if(dateNow.before(estrDate)){
            if(estrDate.time.minus(dateNow.time)>dateNow.time-strDate.time){
                returnString= "Started at "+ android.text.format.DateFormat.format("dd, MMM, HH : mm",strDate)
            }
            else{
                returnString= "Available till "+ android.text.format.DateFormat.format("dd, MMM, HH : mm",strDate)
            }
        }
        else {
            returnString =
                "" + android.text.format.DateFormat.format("dd, MMM, HH : mm",strDate)
        }
    }else {
        returnString =
            "Starts " + android.text.format.DateFormat.format("dd, MMM, HH : mm",strDate)
    }
    return returnString
}

fun getDifference(startDate: Date, endDate: Date) {
    //milliseconds
    var different = endDate.time - startDate.time
    println("startDate : $startDate")
    println("endDate : $endDate")
    println("different : $different")
    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24
    val elapsedDays = different / daysInMilli
    different %= daysInMilli
    val elapsedHours = different / hoursInMilli
    different %= hoursInMilli
    val elapsedMinutes = different / minutesInMilli
    different %= minutesInMilli
    val elapsedSeconds = different / secondsInMilli
    System.out.printf(
        "%d days, %d hours, %d minutes, %d seconds%n",
        elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
    )
}