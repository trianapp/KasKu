package app.trian.kasku.ui.pages.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.formatDate
import app.trian.kasku.ui.Routes
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
fun PageAddWalletSuccess(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val bankViewModel = hiltViewModel<WalletViewModel>()

    val currentBankAccount by bankViewModel.currentBankAccount.observeAsState(initial = null)

    LaunchedEffect(key1 = Unit, block = {
        bankViewModel.getCurrentBankAccount()
    })
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        IconToggleButton(
            checked = false,
            onCheckedChange = {
               router.navigate(Routes.DASHBOARD)
            },
            modifier = modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Octicons.X24,
                contentDescription = "Close"
            )
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
                painter = painterResource(
                    id = R.drawable.bg_congratulations
                ),
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
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        brush = Brush.linearGradient(
                            currentBankAccount?.let {
                                listOf(
                                    HexToJetpackColor.getColor(
                                        it.colorStart
                                    ),
                                    HexToJetpackColor.getColor(
                                        it.colorEnd
                                    )
                                )
                            }?: listOf(
                                HexToJetpackColor.getColor(
                                    listGradient[1].first
                                ),
                                HexToJetpackColor.getColor(
                                    listGradient[1].second
                                )
                            )

                        )
                    )
                    .padding(all = 20.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_category_hotel),
                    contentDescription = "",
                    modifier = modifier
                        .fillMaxSize(fraction = 0.1f)
                        .align(Alignment.TopEnd)
                )
                Column {
                    Column {
                        Text(
                            text = "Bank name",
                            style = MaterialTheme.typography.caption.copy(
                                color = Color.White
                            )
                        )
                        Spacer(modifier = modifier.height(6.dp))
                        Text(
                            text = currentBankAccount?.bankName ?: "",
                            style = MaterialTheme.typography.body2.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "started amount",
                                style = MaterialTheme.typography.caption.copy(
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = modifier.height(6.dp))
                            Text(
                                text = "Rp ${currentBankAccount?.startAmount}",
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Column(
                            modifier = modifier
                                .height(40.dp)
                                .width(1.dp)
                                .background(
                                    MaterialTheme.colors.surface
                                ),
                        ) {

                        }
                        Column {
                            Text(
                                text = "Date",
                                style = MaterialTheme.typography.caption.copy(
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = modifier.height(6.dp))
                            Text(
                                text = currentBankAccount?.created_at?.formatDate("d-MMMM-yyyy") ?: "",
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewAddbankSuccess() {
    KasKuTheme {
        PageAddWalletSuccess(router = rememberNavController())
    }
}
