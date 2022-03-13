package app.trian.kasku.ui.component.calendar.util

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.47
 * site https://trian.app
 */
import java.time.YearMonth

internal operator fun YearMonth.dec() = this.minusMonths(1)

internal operator fun YearMonth.inc() = this.plusMonths(1)