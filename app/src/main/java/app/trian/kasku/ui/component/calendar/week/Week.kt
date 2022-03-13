package app.trian.kasku.ui.component.calendar.week

import app.trian.kasku.ui.component.calendar.day.Day
import javax.annotation.concurrent.Immutable

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.50
 * site https://trian.app
 */

@Immutable
internal data class Week(
    val isFirstWeekOfTheMonth:Boolean=false,
    val days:List<Day>
)