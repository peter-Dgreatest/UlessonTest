package com.example.ulessontest.util

import java.text.SimpleDateFormat
import java.util.*

fun convertTextToTime(time: String,timeEnd:String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    var returnString:String=""
    var stimeString = time.substring(0, time.lastIndexOf('.'))
    var etimeString = timeEnd.substring(0, time.lastIndexOf('.'))
    input.timeZone = TimeZone.getTimeZone("WAT")
    val parsed = input.parse(stimeString)
    val eparsed = input.parse(etimeString)

    val startTimeParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val endTimeParse = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val timeParser = SimpleDateFormat("HH:mm")
    val timeParser2 = SimpleDateFormat("HH")
    val timeParser3 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")

    val strDate = startTimeParse.parse(stimeString)
    val estrDate = startTimeParse.parse(etimeString)
    val dateNow = Date()
    if (dateNow.after(strDate)) {
        if(dateNow.before(estrDate)){
            if(estrDate.time.minus(dateNow.time)>dateNow.time-strDate.time){
                returnString= "Started at "+ timeParser.parse(dateNow.toString())
            }
            else{
                returnString= "Available for "+ timeParser2.parse((estrDate.time-dateNow.time).toString())
            }
        }
        else {
            returnString =
                "" + timeParser3.parse(stimeString)
        }
    }else {
        returnString =
            "Starts " + timeParser3.parse(stimeString)
    }
//    val l = LocalDate.parse(
//        time.substring(0, time.lastIndexOf('.')),
//        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
//    )
//
//    val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
//    val newString = time.substring(time.indexOf('T') + 1, time.indexOf(':', time.indexOf('T') + 4))

    return returnString
}