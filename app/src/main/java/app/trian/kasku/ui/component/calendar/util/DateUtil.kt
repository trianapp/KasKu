package app.trian.kasku.ui.component.calendar.util

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.46
 * site https://trian.app
 */

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal fun Collection<LocalDate>.addOrRemoveIfExists(date: LocalDate) =
    if (contains(date)) {
        this - date
    } else {
        this + date
    }

internal infix fun DayOfWeek.daysUntil(other: DayOfWeek) = (7 + (value - other.value)) % 7

internal fun getDays(
    fromDay:LocalDate = LocalDate.now()
):List<LocalDate> {
    val firstDay = fromDay.minusDays(4)
    val day = listOf(1, 2, 3, 4, 5, 6, 7)
    val days = day.map { day ->
        firstDay.plusDays(day.toLong())
    }

    return days
}

internal fun getMonths(
    fromMonth:YearMonth= YearMonth.now()
):List<YearMonth>{
    val month = listOf(1,2,3,4,5,6)
    val month2 = listOf(7,8,9,10,11,12)

    return if(month.contains(fromMonth.monthValue)){
        month.map {
            YearMonth.of(fromMonth.year,it)
        }
    }else{
        month2.map {
            YearMonth.of(fromMonth.year,it)
        }
    }
}

data class DayLoop(
    var day:Int,
    var next:Boolean
)