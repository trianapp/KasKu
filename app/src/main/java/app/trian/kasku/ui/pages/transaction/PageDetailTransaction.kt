package app.trian.kasku.ui.pages.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarBasic
import app.trian.kasku.ui.component.ButtonSmallSecondary
import app.trian.kasku.ui.component.DottedLine
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Plus24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 13.25
 * site https://trian.app
 */

@Composable
fun PageDetailTransaction(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Transaction",
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            router.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    vertical = 30.dp,
                    horizontal = 30.dp
                )
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.TopCenter)

            ) {
                Column {
                    Text(
                        text = "Payee",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Motorbike engine oil",
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "Category",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Cash",
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "Budget",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Budget name",
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Row (
                    modifier= modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                        ){
                    Column {
                        Text(
                            text = "Transaction type",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Expense",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = modifier
                            .padding(vertical = 4.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(
                                color = MaterialTheme.colors.onSurface
                            )
                    ) {

                    }
                    Column {
                        Text(
                            text = "Date",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "04-16-19",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                Spacer(modifier = modifier
                    .height(30.dp))
                DottedLine(
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = modifier
                    .height(30.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Amount",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Rp 2.000.000",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    ButtonSmallSecondary(
                        text = "Edit",
                        backgroundColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.primary,

                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailTransaction() {
    KasKuTheme {
        PageDetailTransaction(router = rememberNavController())
    }
}