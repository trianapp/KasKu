package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.data.local.entity.Budget
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.ItemListBudget
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.component.ScreenEmptyState
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.launch
import java.time.YearMonth

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@ExperimentalMaterialApi
@Composable
fun PageBudget(
    modifier: Modifier = Modifier,
    budgets:List<Budget> = listOf(),
    router: NavHostController,
    onRestartActivity:()->Unit={}
) {
    val dashboardViewModel = hiltViewModel<DashboardViewModel>()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedMonth by remember {
        mutableStateOf<YearMonth?>(null)
    }

    PageBaseDashboard(
        drawerState=drawerState,
        router = router,
        onRestartActivity = onRestartActivity,
        dashboardViewModel = dashboardViewModel,
        onFabClicked = {
            router.navigate(Routes.CREATE_BUDGET)
        },
        topAppbar = {
            AppbarDashboard(
                title = "Budget",
                content = {
                    MonthPicker(
                        selectedMonth = selectedMonth,
                        onItemSelected = {
                            selectedMonth=it
                        }
                    )
                },
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
                actions = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            router.navigate(Routes.CREATE_BUDGET)
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.Plus24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {}
                    ) {
                        Icon(
                            imageVector = Octicons.Search24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
        content = {
            if(budgets.isEmpty()){
                ScreenEmptyState(
                    image = R.drawable.bg_empty_3,
                    title = "No budget made yet",
                    subtitle ="You can add new budget by tapping button + below"
                )
            }else {
                LazyColumn(content = {
                    items(budgets) { budget ->
                        ItemListBudget(
                            name = budget.name,
                            amount = "Rp ${budget.amount}",
                            percent = 50,
                            color =  MaterialTheme.colors.secondary,
                            usage = "Rp 0",
                            type =BudgetType.INCOME
                        )
                    }
                    item {
                        Spacer(modifier = modifier.height(60.dp))
                    }
                })
            }
        }
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageBudget() {
    KasKuTheme {
        PageBudget(
            router = rememberNavController()
        )
    }
}