package app.trian.kasku.ui.component.calendar.week


import java.time.format.TextStyle.SHORT
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import java.time.DayOfWeek
import java.util.*

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.48
 * site https://trian.app
 */

@Composable
public fun DefaultWeekHeader(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(SHORT, Locale.ROOT),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}
internal fun <T> Array<T>.rotateRight(n: Int): List<T> = takeLast(n) + dropLast(n)