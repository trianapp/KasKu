package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.coloredShadow
import app.trian.kasku.common.formatDate
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.HexToJetpackColor
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.listGradient
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@ExperimentalMaterialApi
@Composable
fun PageProfile(
    modifier: Modifier = Modifier,
    router: NavHostController,
    onRestartActivity:()->Unit={}
) {


    val ctx = LocalContext.current
    val dashboardViewModel = hiltViewModel<DashboardViewModel>()
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val currentBankAccount by dashboardViewModel.currentBankAccount.observeAsState(initial = null)
    val currentUserProfile by dashboardViewModel.currentUserProfile.observeAsState(initial = null)
    LaunchedEffect(key1 = Unit, block = {
        dashboardViewModel.getCurrentBankAccount()
        dashboardViewModel.getCurrentUserProfile()
    })

    PageBaseDashboard(
        drawerState=drawerState,
        router = router,
        onRestartActivity = onRestartActivity,
        dashboardViewModel = dashboardViewModel,
        topAppbar = {
            AppbarProfile(
                onNavigation = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onAction = {
                    router.navigate(Routes.SETTINGS)
                }
            )
        },
        content = {
            Column {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        )
                        .background(MaterialTheme.colors.surface)
                        .padding(horizontal = 30.dp)
                ) {
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(data = currentUserProfile?.authUser?.photoUrl ?:""),
                            contentDescription = stringResource(R.string.content_description_page_profile),
                            modifier = modifier
                                .size(currentWidth / 4)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = modifier.width(16.dp))
                        Column {
                            Text(
                                text = currentUserProfile?.authUser?.displayName ?: "",
                                style = MaterialTheme.typography.h4.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
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
                                } ?: listOf(
                                    HexToJetpackColor.getColor(
                                        listGradient[1].first
                                    ),
                                    HexToJetpackColor.getColor(
                                        listGradient[1].second
                                    )
                                )

                            )
                        )
                        .coloredShadow(
                            MaterialTheme.colors.primary
                        )
                        .padding(all = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = currentBankAccount?.bankName ?: "",
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                text = "Rp ${currentBankAccount?.amount ?: "0"}",
                                style = MaterialTheme.typography.body1.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                        }
                        ButtonSmallSecondary(
                            stringResource(R.string.btn_update),
                            textColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.onPrimary
                        ){
                            router.navigate(Routes.ADD_BANK){

                            }
                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                }
                Spacer(modifier = modifier.height(30.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_email),
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = currentUserProfile?.authUser?.email ?: "",
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_date_of_birth),
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = currentUserProfile?.user?.dateOfBirth.formatDate("dd-MMMM-yyyy"),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageProfile() {
    KasKuTheme {
        PageProfile(router = rememberNavController())
    }
}