package app.trian.kasku.ui.component.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.R
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.component.ItemStat
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 14.13
 * site https://trian.app
 */
@Composable
fun ScreenPickTypeTransaction(
    modifier: Modifier=Modifier,
    text:String="",
    onIncome:()->Unit={},
    onExpenses:()->Unit={}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                vertical = 30.dp,
                horizontal = 30.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboard_3),
                contentDescription = "",
                modifier = modifier.fillMaxWidth(fraction = 0.5f)
            )
        }
        Text(
            text =text,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            ),
            modifier = modifier
                .fillMaxWidth(fraction = 0.8f)
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemStat(
                name = "Income",
                iconColor = MaterialTheme.colors.secondary
            ){
                onIncome()
            }
            ItemStat(
                name = "Expense",
                iconColor = MaterialTheme.colors.primary
            ){

                onExpenses()
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenPickTypeTransaction() {
    KasKuTheme {
        Column(
            Modifier.background(MaterialTheme.colors.background)
        ) {
            ScreenPickTypeTransaction()
        }

    }
}