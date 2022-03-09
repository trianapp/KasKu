package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import app.trian.kasku.ui.theme.KasKuTheme
import com.github.mikephil.charting.charts.LineChart

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.28
 * site https://trian.app
 */

@Composable
fun CardChartHome(
    modifier: Modifier=Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
    ) {
        Column {
            Text(
                text = "Net balance"
            )
            Text(
                text = "Rp 20.000.000"
            )
            AndroidView(
                factory = {
                    LineChart(it).apply {

                    }
                },
                update = {
                    view->

                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardChartHome() {
    KasKuTheme {
        CardChartHome()
    }
}