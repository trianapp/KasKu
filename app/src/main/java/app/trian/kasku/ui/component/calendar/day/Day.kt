package app.trian.kasku.ui.component.calendar.day


import java.time.LocalDate

/**
 * Container for basic info about the displayed day
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.53
 * site https://trian.app
 * @param date local date of the day
 * @param isCurrentDay whenever the day is the today's date
 * @param isFromCurrentMonth whenever the day is from currently rendered month
 */
public interface Day {
    public val date: LocalDate
    public val isCurrentDay: Boolean
    public val isFromCurrentMonth: Boolean
}