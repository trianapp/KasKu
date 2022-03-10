package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarDashboard
import app.trian.kasku.ui.component.ItemTotalTransaction
import app.trian.kasku.ui.component.ItemTransaction
import app.trian.kasku.ui.component.MonthPicker
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@Composable
fun PageDaily(
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
                title = "Daily",
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
                        ItemTransaction(){
                            router.navigate(Routes.DETAIL_TRANSACTION)
                        }
                    }
                    item {
                        ItemTotalTransaction()
                    }
                    item {
                        Spacer(modifier = modifier.height(cardHeight))
                    }
                }
            )
        }
    )
}

@Preview
@Composable
fun PreviewPageDaily() {
    KasKuTheme {
        PageDaily(router = rememberNavController())
    }
}