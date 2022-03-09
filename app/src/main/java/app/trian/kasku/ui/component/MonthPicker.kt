package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.42
 * site https://trian.app
 */
@Composable
fun MonthPicker(
    modifier:Modifier=Modifier,
    onItemSelected:()->Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = ((currentWidth/6)-10.dp)


    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for ( i in 0..5){
            Column(
                modifier = modifier.width(cardWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "2022",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Box(
                    modifier = modifier
                        .width(cardWidth)
                        .clip(MaterialTheme.shapes.medium)
                        .background(if(i == 2 ) MaterialTheme.colors.primary else BackgroundDashboard)
                        .clickable {
                            onItemSelected()
                        }
                        .padding(
                            horizontal = 6.dp,
                            vertical = 8.dp
                        )
                ) {
                    Text(
                        text = "Jan",
                        style = MaterialTheme.typography.caption.copy(
                            color = if(i == 2 ) MaterialTheme.colors.surface else MaterialTheme.colors.onBackground
                        ),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMOnthPicker() {
    KasKuTheme {
        MonthPicker()
    }
}