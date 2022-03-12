package app.trian.kasku.ui.component.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.trian.kasku.ui.component.calendar.day.DayState
import app.trian.kasku.ui.component.calendar.day.DefaultDay
import app.trian.kasku.ui.component.calendar.header.DefaultMonthHeader
import app.trian.kasku.ui.component.calendar.header.MonthState
import app.trian.kasku.ui.component.calendar.month.DaysOfWeek
import app.trian.kasku.ui.component.calendar.month.MonthContent
import app.trian.kasku.ui.component.calendar.month.MonthPager
import app.trian.kasku.ui.component.calendar.selection.DynamicSelectionState
import app.trian.kasku.ui.component.calendar.selection.EmptySelectionState
import app.trian.kasku.ui.component.calendar.selection.SelectionMode
import app.trian.kasku.ui.component.calendar.selection.SelectionState
import app.trian.kasku.ui.component.calendar.util.MonthHeaderPosition
import app.trian.kasku.ui.component.calendar.week.DefaultWeekHeader
import app.trian.kasku.ui.component.calendar.week.rotateRight
import com.google.accompanist.pager.ExperimentalPagerApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

/**
 * State of the calendar composable
 *
 * @property monthState currently showed month
 * @property selectionState handler for the calendar's selection
 */
@Stable
public class CalendarState<T : SelectionState>(
    public val monthState: MonthState,
    public val selectionState: T,
)

/**
 * [Calendar] implementation using a [DynamicSelectionState] as a selection handler.
 *
 *  * Basic usage:
 * ```
 *  @Composable
 *  fun MainScreen() {
 *    SelectableCalendar()
 *  }
 * ```
 *
 * @param modifier
 * @param firstDayOfWeek first day of a week, defaults to current locale's
 * @param today current day, defaults to [LocalDate.now]
 * @param showAdjacentMonths whenever to show or hide the days from adjacent months
 * @param horizontalSwipeEnabled whenever user is able to change the month by horizontal swipe
 * @param calendarState state of the composable
 * @param dayContent composable rendering the current day
 * @param monthHeader header for showing the current month and controls for changing it
 * @param weekHeader header for showing captions for each day of week
 * @param monthContainer container composable for all the days in current month
 */
@ExperimentalPagerApi
@Composable
public fun SelectableCalendar(
    modifier: Modifier = Modifier,
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    today: LocalDate = LocalDate.now(),
    showAdjacentMonths: Boolean = true,
    horizontalSwipeEnabled: Boolean = true,
    monthHeaderPosition: MonthHeaderPosition=MonthHeaderPosition.TOP,
    calendarState: CalendarState<DynamicSelectionState> = rememberSelectableCalendarState(),
    dayContent: @Composable BoxScope.(DayState<DynamicSelectionState>) -> Unit = { DefaultDay(it) },
    monthHeader: @Composable ColumnScope.(MonthState) -> Unit = { DefaultMonthHeader(it) },
    weekHeader: @Composable BoxScope.(List<DayOfWeek>) -> Unit = { DefaultWeekHeader(it) },
    monthContainer: @Composable (content: @Composable (PaddingValues) -> Unit) -> Unit = { content ->
        Box { content(PaddingValues()) }
    },
) {
    Calendar(
        modifier = modifier,
        firstDayOfWeek = firstDayOfWeek,
        today = today,
        showAdjacentMonths = showAdjacentMonths,
        horizontalSwipeEnabled = horizontalSwipeEnabled,
        monthHeaderPosition=monthHeaderPosition,
        calendarState = calendarState,
        dayContent = dayContent,
        monthHeader = monthHeader,
        weekHeader = weekHeader,
        monthContainer = monthContainer
    )
}

/**
 * [Calendar] implementation without any mechanism for the selection.
 *
 * Basic usage:
 * ```
 *  @Composable
 *  fun MainScreen() {
 *    StaticCalendar()
 *  }
 * ```
 *
 * @param modifier
 * @param firstDayOfWeek first day of a week, defaults to current locale's
 * @param today current day, defaults to [LocalDate.now]
 * @param showAdjacentMonths whenever to show or hide the days from adjacent months
 * @param horizontalSwipeEnabled whenever user is able to change the month by horizontal swipe
 * @param calendarState state of the composable
 * @param dayContent composable rendering the current day
 * @param monthHeader header for showing the current month and controls for changing it
 * @param weekHeader header for showing captions for each day of week
 * @param monthContainer container composable for all the days in current month
 */
@ExperimentalPagerApi
@Composable
public fun StaticCalendar(
    modifier: Modifier = Modifier,
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    today: LocalDate = LocalDate.now(),
    showAdjacentMonths: Boolean = true,
    horizontalSwipeEnabled: Boolean = true,
    monthHeaderPosition: MonthHeaderPosition=MonthHeaderPosition.TOP,
    calendarState: CalendarState<EmptySelectionState> = rememberCalendarState(),
    dayContent: @Composable BoxScope.(DayState<EmptySelectionState>) -> Unit = { DefaultDay(it) },
    monthHeader: @Composable ColumnScope.(MonthState) -> Unit = { DefaultMonthHeader(it) },
    weekHeader: @Composable BoxScope.(List<DayOfWeek>) -> Unit = { DefaultWeekHeader(it) },
    monthContainer: @Composable (content: @Composable (PaddingValues) -> Unit) -> Unit = { content ->
        Box { content(PaddingValues()) }
    },
) {
    Calendar(
        modifier = modifier,
        firstDayOfWeek = firstDayOfWeek,
        today = today,
        showAdjacentMonths = showAdjacentMonths,
        horizontalSwipeEnabled = horizontalSwipeEnabled,
        monthHeaderPosition=monthHeaderPosition,
        calendarState = calendarState,
        dayContent = dayContent,
        monthHeader = monthHeader,
        weekHeader = weekHeader,
        monthContainer = monthContainer
    )
}



/**
 * Composable for showing a calendar. The calendar state has to be provided by the call site. If you
 * want to use built-in implementation, check out:
 * [SelectableCalendar] - calendar composable handling selection that can be changed at runtime
 * [StaticCalendar] - calendar without any mechanism for selection
 *
 * @param modifier
 * @param firstDayOfWeek first day of a week, defaults to current locale's
 * @param today current day, defaults to [LocalDate.now]
 * @param showAdjacentMonths whenever to show or hide the days from adjacent months
 * @param horizontalSwipeEnabled whenever user is able to change the month by horizontal swipe
 * @param calendarState state of the composable
 * @param dayContent composable rendering the current day
 * @param monthHeader header for showing the current month and controls for changing it
 * @param weekHeader header for showing captions for each day of week
 * @param monthContainer container composable for all the days in current month
 */
@ExperimentalPagerApi
@SuppressLint("RememberReturnType")
@Composable
public fun <T : SelectionState> Calendar(
    calendarState: CalendarState<T>,
    modifier: Modifier = Modifier,
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    today: LocalDate = LocalDate.now(),
    showAdjacentMonths: Boolean = true,
    horizontalSwipeEnabled: Boolean = true,
    monthHeaderPosition: MonthHeaderPosition=MonthHeaderPosition.TOP,
    dayContent: @Composable BoxScope.(DayState<T>) -> Unit = { DefaultDay(it) },
    monthHeader: @Composable ColumnScope.(MonthState) -> Unit = { DefaultMonthHeader(it) },
    weekHeader: @Composable BoxScope.(List<DayOfWeek>) -> Unit = { DefaultWeekHeader(it) },
    monthContainer: @Composable (content: @Composable (PaddingValues) -> Unit) -> Unit = { content ->
        Box { content(PaddingValues()) }
    },
) {

    val daysOfWeek = remember(firstDayOfWeek) {
        DayOfWeek.values().rotateRight(DaysOfWeek - firstDayOfWeek.ordinal)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(monthHeaderPosition == MonthHeaderPosition.TOP) {
            monthHeader(calendarState.monthState)
        }

        Divider()
        if (horizontalSwipeEnabled) {
            MonthPager(
                showAdjacentMonths = showAdjacentMonths,
                monthState = calendarState.monthState,
                selectionState = calendarState.selectionState,
                today = today,
                daysOfWeek = daysOfWeek,
                dayContent = dayContent,
                weekHeader = weekHeader,
                monthContainer = monthContainer,
            )
        } else {
            MonthContent(
                currentMonth = calendarState.monthState.currentMonth,
                showAdjacentMonths = showAdjacentMonths,
                selectionState = calendarState.selectionState,
                today = today,
                daysOfWeek = daysOfWeek,
                dayContent = dayContent,
                weekHeader = weekHeader,
                monthContainer = monthContainer,
            )
        }

        Divider()
        if(monthHeaderPosition == MonthHeaderPosition.BOTTOM) {
            monthHeader(calendarState.monthState)
        }
    }
}

/**
 * Helper function for providing a [CalendarState] implementation with selection mechanism.
 *
 * @param initialMonth initially rendered month
 * @param initialSelection initial selection of the composable
 * @param initialSelectionMode initial mode of the selection
 * @param onSelectionChanged callback for side effects triggered when the selection state changes
 */
@Composable
public fun rememberSelectableCalendarState(
    initialMonth: YearMonth = YearMonth.now(),
    initialSelection: List<LocalDate> = emptyList(),
    initialSelectionMode: SelectionMode = SelectionMode.Single,
    onSelectionChanged: (List<LocalDate>) -> Unit = {},
    monthState: MonthState = rememberSaveable(saver = MonthState.Saver()) {
        MonthState(initialMonth = initialMonth)
    },
    selectionState: DynamicSelectionState = rememberSaveable(
        saver = DynamicSelectionState.Saver(
            onSelectionChanged
        )
    ) {
        DynamicSelectionState(onSelectionChanged, initialSelection, initialSelectionMode)
    },
): CalendarState<DynamicSelectionState> = remember { CalendarState(monthState, selectionState) }

/**
 * Helper function for providing a [CalendarState] implementation without a selection mechanism.
 *
 * @param initialMonth initially rendered month
 */
@Composable
public fun rememberCalendarState(
    initialMonth: YearMonth = YearMonth.now(),
    monthState: MonthState = rememberSaveable(saver = MonthState.Saver()) {
        MonthState(initialMonth = initialMonth)
    },
): CalendarState<EmptySelectionState> = remember { CalendarState(monthState, EmptySelectionState) }