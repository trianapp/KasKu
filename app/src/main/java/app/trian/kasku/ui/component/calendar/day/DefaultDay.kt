package app.trian.kasku.ui.component.calendar.day

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.component.calendar.selection.SelectionState
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.53
 * site https://trian.app
 */
/**
 * Default implementation for day content. It supports different appearance for days from
 * current and adjacent month, as well as current day and selected day
 *
 * @param selectionColor color of the border, when day is selected
 * @param currentDayColor color of content for the current date
 * @param onClick callback for interacting with day clicks
 */
@Composable
public fun <T : SelectionState> DefaultDay(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    selectionColor: Color = MaterialTheme.colors.secondary,
    currentDayColor: Color = MaterialTheme.colors.primary,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)

    val color = when{
        isSelected -> selectionColor
        date == LocalDate.now() -> currentDayColor
        else -> MaterialTheme.colors.onBackground
    }

        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(2.dp)
                .clickable {
                onClick(date)
                selectionState.onDateSelected(date)
            },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Light,
                    color = color
                )
            )
        }

}
/**
 * Default implementation for day content week mode calendar. It supports different appearance for days from
 * current and adjacent month, as well as current day and selected day
 *
 * @param selectionColor color of the border, when day is selected
 * @param currentDayColor color of content for the current date
 * @param onClick callback for interacting with day clicks
 */
@Composable
public fun <T : SelectionState> DefaultDayWeekMode(
    state: DayState<T>,
    modifier: Modifier = Modifier,
    selectionColor: Color = MaterialTheme.colors.secondary,
    currentDayColor: Color = MaterialTheme.colors.primary,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState

    val isSelected = selectionState.isDateSelected(date)

    Card(
        modifier = modifier
            .padding(4.dp),
        elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
        contentColor = if (isSelected) selectionColor else contentColorFor(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Box(
            modifier = Modifier.clickable {
                onClick(date)
                selectionState.onDateSelected(date)
            }.padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = date.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.body1
                )
                Text(text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
            }
        }
    }
}