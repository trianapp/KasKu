package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.ItemBudget
import app.trian.kasku.ui.component.ItemStat
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Plus24
import compose.icons.octicons.Search24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@Composable
fun PageBudget(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardHeight = currentWidth / 5 - 10.dp
    PageBaseDashboard(
        router = router,
        topAppbar = {
            AppbarDashboard(
                title = "Budget",
                content = {
                    MonthPicker()
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
                    ItemBudget(
                        name = "Budget name",
                        amount = if(index / 2 == 0) "Rp 500.000" else "Rp 1.000.000",
                        percent = 50,
                        color = if(index / 2 == 0) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
                        usage = "Rp 100.000",
                        type = if(index / 2 == 0) BudgetType.EXPENSE else BudgetType.INCOME
                    )
                }
                item {
                    Spacer(modifier = modifier.height(cardHeight))
                }
            })
        }
    )
}

@Preview
@Composable
fun PreviewPageBudget() {
    KasKuTheme {
        PageBudget(router = rememberNavController())
    }
}