package app.trian.kasku.ui.pages.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.ButtonSmallSecondary
import app.trian.kasku.ui.component.DottedLine
import app.trian.kasku.ui.component.coloredShadow
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.HexToJetpackColor
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.listGradient
import compose.icons.Octicons
import compose.icons.octicons.X24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.27
 * site https://trian.app
 */
@Composable
fun PageAddTransactionSuccess(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        IconToggleButton(
            checked = false,
            onCheckedChange = {
               router.popBackStack()
            },
            modifier = modifier.align(Alignment.TopStart)
        ) {
            Icon(imageVector = Octicons.X24, contentDescription = "Close")
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_congratulations),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Congratulation!",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Your bank account is added successfully to the app",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(30.dp))
            Column {
                Column(
                    modifier = modifier

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
}

@Preview
@Composable
fun PreviewPageAddTransactionSuccess() {
    KasKuTheme {
        PageAddTransactionSuccess(router = rememberNavController())
    }
}
