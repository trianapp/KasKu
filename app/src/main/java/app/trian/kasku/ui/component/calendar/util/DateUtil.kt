package app.trian.kasku.ui.component.calendar.util

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.46
 * site https://trian.app
 */

import java.time.DayOfWeek
import java.time.LocalDate

internal fun Collection<LocalDate>.addOrRemoveIfExists(date: LocalDate) =
    if (contains(date)) {
        this - date
    } else {
        this + date
    }

internal infix fun DayOfWeek.daysUntil(other: DayOfWeek) = (7 + (value - other.value)) % 7