package app.trian.kasku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.R

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 01.49
 * site https://trian.app
 */

@Composable
fun HeaderStat(
    modifier: Modifier=Modifier,
    type: BudgetType=BudgetType.INCOME
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val color = when(type){
        BudgetType.EXPENSE -> ExpensesColor
        BudgetType.INCOME -> MaterialTheme.colors.secondary
    }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(currentWidth / 3),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .width(10.dp)
                .height(currentWidth / 3)
                .clip(
                    RoundedCornerShape(
                        bottomEndPercent = 100,
                        topEndPercent = 100
                    )
                )
                .background(
                    color = color
                )
        ) {

        }
        Box(
            modifier = modifier
                .width(currentWidth - 50.dp)
                .height(currentWidth/3)
                .clip(MaterialTheme.shapes.large)
                .background(
                    color = color
                )
                .padding(horizontal = 16.dp)

        ) {

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(currentWidth/3)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "May",
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.White
                    )
                )
                Text(
                    text = "Rp 1.500.000",
                    style = MaterialTheme.typography.h4.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                LinearProgressIndicator(
                    progress = 0.7f,
                    color = Color.White
                )
            }
            Image(
                painter = painterResource(id =R.drawable.ic_coin),
                contentDescription = "",
                modifier = modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .size((currentWidth / 3)/2)
            )
        }
        Box(
            modifier = modifier
                .width(10.dp)
                .height(currentWidth / 3)
                .clip(
                    RoundedCornerShape(
                        bottomStartPercent = 100,
                        topStartPercent = 100
                    )
                )
                .background(
                    color = color
                )
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewHeaderStat() {
    KasKuTheme {
        HeaderStat()
    }
}