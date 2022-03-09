package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 23.20
 * site https://trian.app
 */
@Composable
fun ItemBudget(
    modifier: Modifier=Modifier,
    name:String="",
    amount:String="",
    percent:Int=0,
    usage:String="",
    type:BudgetType=BudgetType.EXPENSE,
    color:Color= Color.White
) {
    Spacer(modifier = modifier.height(10.dp))
    Box(
        modifier = modifier.padding(
            top = 10.dp,
            end = 20.dp,
            start = 20.dp
        )
    ) {
        Box (
            modifier = modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colors.surface)
                .clickable { }
                .padding(
                    all = 20.dp
                )
        ){
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row (
                        modifier = modifier.fillMaxWidth(fraction = 0.7f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ){
                        Text(
                            text = amount,
                            style = MaterialTheme.typography.h4.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = modifier.width(6.dp))
                        Text(
                            text = "$percent %",
                            style = MaterialTheme.typography.body2.copy(
                                color = when(type){
                                    BudgetType.EXPENSE -> ExpensesColor
                                    BudgetType.INCOME -> MaterialTheme.colors.onSurface
                                }
                            )
                        )
                    }
                    Text(
                        text = usage,
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                LinearProgressIndicator(
                    progress = 0.5f,
                    color = when(type){
                        BudgetType.EXPENSE -> ExpensesColor
                        BudgetType.INCOME -> MaterialTheme.colors.secondary
                    },
                    modifier = modifier.fillMaxWidth()
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewItemBudget() {
    KasKuTheme {
        ItemBudget(
            name = "Budget name",
            amount = "Rp 1.000.000",
            percent = 50,
            usage = "Rp 500.000",
            color = MaterialTheme.colors.secondary
        )
    }
}