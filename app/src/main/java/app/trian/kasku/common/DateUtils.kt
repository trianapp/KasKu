package app.trian.kasku.common

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

fun String?.toOffsetDateTime():OffsetDateTime?{
    return this?.let {
        return dateTimeFormatter.parse(it,OffsetDateTime::from)
    }
}

fun OffsetDateTime?.fromOffsetDateTime():String?{

    return this?.format(dateTimeFormatter)
}


fun LocalDate?.toReadableDate(pattern:String="d MMMM, yyyy"):String{
    if(this == null) return  ""
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

fun OffsetDateTime?.toReadableDate(pattern:String="d MMMM, yyyy"):String{
    if(this == null) return "No deadline"
    return this.formatDate(pattern)
}


fun OffsetDateTime.getDateUntil(to:OffsetDateTime):String{
    return "${this.formatDate("d MMM")} - ${to.formatDate("d MMM, yyyy")}"

}
fun OffsetDateTime?.formatDate(pattern: String):String{
    if(this == null) return ""
    if(pattern.isBlank()){
        return this.format(DateTimeFormatter.BASIC_ISO_DATE)
    }
    return this.format(DateTimeFormatter.ofPattern(pattern))
}