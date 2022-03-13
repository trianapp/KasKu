package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.component.calendar.util.getDays
import app.trian.kasku.ui.component.calendar.util.getMonths
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.42
 * site https://trian.app
 */
@Composable
fun MonthPicker(
    modifier:Modifier=Modifier,
    selectedMonth:YearMonth?=null,
    onItemSelected:(YearMonth)->Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = ((currentWidth/6)-10.dp)
    val currentMonth = YearMonth.now()


    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        getMonths(
            fromMonth = YearMonth.now()
        ).forEach{
            Column(
                modifier = modifier.width(cardWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = it.year.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Box(
                    modifier = modifier
                        .width(cardWidth)
                        .clip(MaterialTheme.shapes.medium)
                        .background(
                            when {
                                selectedMonth == it -> MaterialTheme.colors.primary
                                currentMonth == it -> MaterialTheme.colors.secondary
                                else -> BackgroundDashboard
                            }
                        )
                        .clickable {
                            onItemSelected(it)
                        }
                        .padding(
                            horizontal = 6.dp,
                            vertical = 8.dp
                        )
                ) {
                    Text(
                        text = it.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                        style = MaterialTheme.typography.caption.copy(
                            color = when {
                                selectedMonth == it -> MaterialTheme.colors.surface
                                currentMonth == it -> MaterialTheme.colors.surface
                                else -> MaterialTheme.colors.onBackground
                            }
                        ),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun MonthDayAppbar(
    modifier:Modifier=Modifier,
    selected:LocalDate?=null,
    onItemSelected:(LocalDate)->Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = ((currentWidth/8))
    val today = LocalDate.now()


    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        getDays().forEach {
            Column(
                modifier = modifier.width(cardWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = it.year.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(6.dp))
                Box(
                    modifier = modifier
                        .size(cardWidth)
                        .clip(CircleShape)
                        .background(
                            when {
                                selected == it -> MaterialTheme.colors.primary
                                today == it -> MaterialTheme.colors.secondary
                                else -> BackgroundDashboard
                            }
                        )
                        .clickable {
                            onItemSelected(it)
                        }
                        .padding(
                            horizontal = 2.dp,
                            vertical = 2.dp
                        )
                ) {
                    Text(
                        text = it.dayOfMonth.toString(),
                        style = MaterialTheme.typography.caption.copy(
                            color =  when {
                                selected == it -> MaterialTheme.colors.surface
                                today == it -> MaterialTheme.colors.surface
                                else -> MaterialTheme.colors.onBackground
                            }
                        ),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = it.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    style = MaterialTheme.typography.caption.copy(
                        color =  when {
                            selected == it -> MaterialTheme.colors.primary
                            today == it -> MaterialTheme.colors.secondary
                            else -> MaterialTheme.colors.onBackground
                        }
                    ),
                    modifier = modifier
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewMonthPicker() {
    KasKuTheme {
        Column {
            MonthPicker()
            MonthDayAppbar()
        }
    }
}