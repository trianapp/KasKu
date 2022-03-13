package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.CardChartHome
import app.trian.kasku.ui.component.ItemStat
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Question24
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch
import java.time.YearMonth

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.21
 * site https://trian.app
 */
@ExperimentalMaterialApi
@Composable
fun PageHome(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedMonth by remember {
        mutableStateOf<YearMonth?>(null)
    }
    PageBaseDashboard(
        drawerState=drawerState,
        router = router,
        topAppbar = {
                    AppbarDashboard(
                        title = "Home",
                        navigationIcon = {
                            IconToggleButton(
                                checked = false,
                                onCheckedChange = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Octicons.Quote24,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }
                        },
                        content = {
                            MonthPicker(
                                selectedMonth = selectedMonth,
                                onItemSelected = {
                                    selectedMonth=it
                                }
                            )
                        }
                    )
        },
        content = {
            Column (
                modifier = modifier.padding(vertical = 10.dp)
                    ){
                Box(
                    modifier = modifier.padding(
                        horizontal = 30.dp
                    )
                ) {
                    CardChartHome()
                }
                Spacer(modifier = modifier.height(30.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ItemStat(
                        name = "Income",
                        value = "Rp 1.000.00",
                        iconColor = MaterialTheme.colors.secondary
                    ){
                        router.navigate(Routes.STAT_INCOME)
                    }
                    ItemStat(
                        name = "Expense",
                        value = "Rp 1.000.00",
                        iconColor = ExpensesColor
                    ){
                        router.navigate(Routes.STAT_EXPENSE)
                    }
                }
            }
        }
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageHome() {
    KasKuTheme {
        PageHome(router = rememberNavController())
    }
}