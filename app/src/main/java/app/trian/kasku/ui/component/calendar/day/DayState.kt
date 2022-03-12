package app.trian.kasku.ui.component.calendar.day


import androidx.compose.runtime.Stable
import app.trian.kasku.ui.component.calendar.selection.EmptySelectionState
import app.trian.kasku.ui.component.calendar.selection.SelectionState

public typealias NonSelectableDayState = DayState<EmptySelectionState>
/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.53
 * site https://trian.app
 *
 * Contains information about current selection as well as date of rendered day
 */
@Stable
public data class DayState<T : SelectionState>(
    private val day: Day,
    val selectionState: T,
) : Day by day