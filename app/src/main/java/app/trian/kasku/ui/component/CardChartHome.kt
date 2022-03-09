package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

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
    fun data():List<Entry>{
        var datas = listOf<Entry>()
        for (i in 0..20){
            datas = datas + Entry(i.toFloat(),(0..50).random().toFloat())
        }
        return datas
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
        ) {

            Column (
                modifier = modifier.fillMaxWidth()
                    .padding(horizontal = 30.dp),
                    ){
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = "Net balance",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Text(
                    text = "Rp 20.000.000",
                    style = MaterialTheme.typography.body1.copy(
                        color=MaterialTheme.colors.onBackground
                    )
                )
                Spacer(modifier = modifier.height(16.dp))
            }
            AndroidView(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.5f),
                factory = {
                    LineChart(it).apply {
                        axisRight.apply {
                            isEnabled = false
                            setDrawAxisLine(false)
                            setDrawGridLines(false)
                        }
                        axisLeft.apply {
                            //formatter
                            isEnabled = false
                            setDrawAxisLine(false)
                            setDrawGridLines(false)
                        }
                        xAxis.apply {
                            isEnabled = false
                            //disable axis
                            setDrawGridLines(false)
                            position = XAxis.XAxisPosition.BOTTOM
                            //
                            labelRotationAngle = 0f

                            granularity = 1f

                            axisMaximum = 20 + 0.1f
                            //

                        }
                    }
                },
                update = {
                    view->
                    val lineDataSet = LineDataSet(data(),"")
                    lineDataSet.apply {
                        //make chart smooth
                        mode = LineDataSet.Mode.CUBIC_BEZIER
                        cubicIntensity = 0.1f

                        //set transparencyFF18A0FB
                        setColor(0x18A0FB, 1000)
                        //set value in each circle
                        setDrawValues(false)
                        //Part4 set color fill (area)
                        setDrawFilled(true)
                        setDrawCircles(false)

                        lineWidth = 3f

                    }


                    //set data
                    view.data = LineData(lineDataSet)

                    view.invalidate()

                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardChartHome() {
    KasKuTheme {
        Scaffold(
            backgroundColor = BackgroundDashboard
        ) {
            CardChartHome()
        }
    }
}