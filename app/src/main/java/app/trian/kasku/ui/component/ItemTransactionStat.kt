package app.trian.kasku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import app.trian.kasku.R
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.theme.DisableColor
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 20.03
 * site https://trian.app
 */

@Composable
fun ItemTransactionStat(
    modifier: Modifier=Modifier,
    type: BudgetType=BudgetType.INCOME,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardHeight = currentWidth / 5 - 10.dp
    val color = when(type){
        BudgetType.EXPENSE -> ExpensesColor
        BudgetType.INCOME -> MaterialTheme.colors.secondary
    }
    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
            .fillMaxWidth()
            .height(cardHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = modifier
                .size(currentWidth / 6)
                .clip(CircleShape)
                .background(DisableColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_category_autonomous_car),
                contentDescription ="",
                modifier = modifier
                    .size(cardHeight - cardHeight / 3)
            )
        }
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier= modifier
                .height(cardHeight)
                .width(currentWidth - cardHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(
                        fraction = 0.6f
                    )
                ) {
                    Text(
                        text = "Grauthier Drewit",
                        style = MaterialTheme.typography.body2.copy(

                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    LinearProgressIndicator(
                        color = color,
                        progress = 0.8f
                    )
                }
                Text(
                    text = "Rp 100.000",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.secondary
                    )
                )
            }
            Divider()


        }


    }
}


@Preview
@Composable
fun PreviewItemTransactionStat() {
    KasKuTheme {
        Column {
            ItemTransactionStat()

        }

    }
}