package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
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
import app.trian.kasku.ui.component.calendar.DialogCalendarPickerFullScreen
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun PageDaily(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialogDatePicker by remember {
        mutableStateOf(false)
    }

    DialogCalendarPickerFullScreen(
        show = showDialogDatePicker,
        onDismiss = {
            showDialogDatePicker=false
        },
        onDateSelected = {

        }
    )

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
                },
                actions = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            showDialogDatePicker = true
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.Calendar24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
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

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewPageDaily() {
    KasKuTheme {
        PageDaily(router = rememberNavController())
    }
}