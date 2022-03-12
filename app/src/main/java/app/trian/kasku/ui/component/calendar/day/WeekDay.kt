package app.trian.kasku.ui.component.calendar.day

import androidx.compose.runtime.Immutable
import java.time.LocalDate

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.53
 * site https://trian.app
 */
@Immutable
internal class WeekDay(
    override val date: LocalDate,
    override val isCurrentDay: Boolean,
    override val isFromCurrentMonth: Boolean
) : Day