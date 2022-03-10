package app.trian.kasku.ui.pages.stat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.R
import app.trian.kasku.ui.Routes
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
                item {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 20.dp,
                                horizontal = 20.dp
                            ),
                    ) {
                        Text(
                            text = "Transactions",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                    }
                }
                items(count = 3){
                    index->
                    ItemTransactionStat(
                        type = type
                    ){
                        router.navigate(Routes.DETAIL_TRANSACTION)
                    }
                }
                item {
                    Spacer(modifier = modifier.height(20.dp))
                    Row (
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 20.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                     ItemOptionStat(
                         name = "Export",
                         icon = R.drawable.ic_hotel
                     ){}
                     ItemOptionStat(
                         name = "Share",
                         icon = R.drawable.ic_cutlery
                     ){}
                    }
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