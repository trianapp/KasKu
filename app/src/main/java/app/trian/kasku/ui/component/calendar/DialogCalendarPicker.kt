package app.trian.kasku.ui.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.trian.kasku.ui.component.calendar.day.DefaultDay
import app.trian.kasku.ui.component.calendar.util.MonthHeaderPosition
import com.google.accompanist.pager.ExperimentalPagerApi
import java.time.LocalDate

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 23.46
 * site https://trian.app
 */
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun DialogCalendarPicker(
    show:Boolean=false,
    modifier: Modifier=Modifier,
    onDismiss:()->Unit={},
    onDateSelected:(LocalDate)->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardHeight = currentWidth
    val calendarState = rememberSelectableCalendarState()

    if(show){
        Dialog(
            onDismissRequest =onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = modifier
                    .padding(
                        horizontal = 30.dp
                    )
            ) {
                Column(
                    modifier = modifier
                        .height(cardHeight)
                        .clip(MaterialTheme.shapes.large)
                        .background(color = MaterialTheme.colors.surface)
                        .padding(
                            all = 20.dp,
                        )
                ) {
                    Text(
                        text = "Select date transaction",
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    SelectableCalendar(
                        monthHeaderPosition = MonthHeaderPosition.BOTTOM,
                        calendarState = calendarState,
                        showAdjacentMonths = false,
                        dayContent = {
                                day->
                            DefaultDay(
                                state = day,
                                onClick = {
                                    onDateSelected(it)
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}