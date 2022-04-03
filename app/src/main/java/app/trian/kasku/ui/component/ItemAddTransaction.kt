package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 21.49
 * site https://trian.app
 */
data class ItemDetailTransactionModel(
    var label:String,
    var value:String,
    var isWithAmount:Boolean=false,
    var amountLabel:String="",
    var amountValue:String=""
)

@Composable
fun ItemAddTransaction(
    modifier:Modifier=Modifier,
    iconColor:Color= MaterialTheme.colors.onSurface,
    transactionType:String,
    amountName:String="",
    amount:String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier.width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(iconColor)
            ) {

            }
            Spacer(modifier = modifier.width(10.dp))
            Column(
                modifier=modifier
            ) {
                Text(
                    text="Transaction type",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = transactionType,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onBackground
                    )
                )
            }
        }
        Spacer(modifier = modifier.width(10.dp))
        Column {
            Text(
                text=amountName,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "Rp $amount",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
        }

    }
}

@Composable
fun ItemAddTransaction(
    modifier:Modifier=Modifier,
    iconColor:Color= MaterialTheme.colors.onSurface,
    name:String="",
    value:String=""
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(iconColor)
        ) {

        }

        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier=modifier
        ) {
            Text(
                text=name,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )
        }

    }
}

@Preview
@Composable
fun PreviewItemAddTransaction() {
    KasKuTheme {
        Column {
            ItemAddTransaction(
                transactionType = "Expenses",
                amount = "Rp 1.000.000.000"
            )
            Spacer(modifier = Modifier.height(30.dp))
            ItemAddTransaction(
                name = "Category",
                value = "Cash"
            )
        }
    }
}