package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.ItemListBudget
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.launch

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
    router: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    PageBaseDashboard(
        drawerState=drawerState,
        router = router,
        topAppbar = {
            AppbarDashboard(
                title = "Budget",
                content = {
                    MonthPicker()
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
            LazyColumn(content = {
                items(count = 3){
                    index->
                    ItemListBudget(
                        name = "Budget name",
                        amount = if(index / 2 == 0) "Rp 500.000" else "Rp 1.000.000",
                        percent = 50,
                        color = if(index / 2 == 0) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
                        usage = "Rp 100.000",
                        type = if(index / 2 == 0) BudgetType.EXPENSE else BudgetType.INCOME
                    )
                }
                item {
                    Spacer(modifier = modifier.height(60.dp))
                }
            })
        }
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageBudget() {
    KasKuTheme {
        PageBudget(router = rememberNavController())
    }
}