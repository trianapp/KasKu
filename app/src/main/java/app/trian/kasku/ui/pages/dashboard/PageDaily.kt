package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.ItemTotalDailyTransaction
import app.trian.kasku.ui.component.ItemListDailyTransaction
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@ExperimentalMaterialApi
@Composable
fun PageDaily(
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
                title = "Daily",
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
                    MonthPicker()
                }
            )
        },
        content = {
            LazyColumn(
                content = {
                    items(count = 7){
                        index->
                        ItemListDailyTransaction(){
                            router.navigate(Routes.DETAIL_TRANSACTION)
                        }
                    }
                    item {
                        ItemTotalDailyTransaction()
                    }
                    item {
                        Spacer(modifier = modifier.height(60.dp))
                    }
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageDaily() {
    KasKuTheme {
        PageDaily(router = rememberNavController())
    }
}