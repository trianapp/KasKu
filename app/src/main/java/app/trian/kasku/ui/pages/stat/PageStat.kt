package app.trian.kasku.ui.pages.stat

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.HeaderStat
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Search24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 01.38
 * site https://trian.app
 */

@Composable
fun PageStat(
    modifier: Modifier = Modifier,
    router: NavHostController,
    type: BudgetType=BudgetType.INCOME
) {
    Scaffold(
        topBar = {
            AppbarDashboard(
                title=when(type){
                    BudgetType.EXPENSE -> "Expense"
                    BudgetType.INCOME -> "Income"
                },
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {}) {
                        Icon(imageVector = Octicons.ArrowLeft24, contentDescription = "")
                    }
                },
                actions = {
                    IconToggleButton(checked = false, onCheckedChange = {}) {
                        Icon(imageVector = Octicons.Search24, contentDescription = "")
                    }
                },
                content = {
                    MonthPicker()
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        LazyColumn(
            content = {
                item{
                    Spacer(modifier = modifier.height(20.dp))
                }
                item {
                    HeaderStat(
                        type = type
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewPageStat() {
    KasKuTheme {
        PageStat(router = rememberNavController())
    }
}