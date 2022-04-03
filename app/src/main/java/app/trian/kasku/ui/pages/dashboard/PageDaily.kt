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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.data.local.entity.Transaction
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.component.calendar.DialogCalendarPickerFullScreen
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch
import java.time.LocalDate

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
    transactions:List<Transaction> = listOf(),
    router: NavHostController,
    onRestartActivity:()->Unit={}
) {
    val dashboardViewModel = hiltViewModel<DashboardViewModel>()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialogDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    DialogCalendarPickerFullScreen(
        show = showDialogDatePicker,
        onDismiss = {
            showDialogDatePicker=false
        },
        onDateSelected = {
            selectedDate = it
            showDialogDatePicker = false
        }
    )

    PageBaseDashboard(
        drawerState=drawerState,
        router = router,
        onRestartActivity = onRestartActivity,
        dashboardViewModel = dashboardViewModel,
        onFabClicked = {
            router.navigate(Routes.ADD_TRANSACTION)
        },
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
                    MonthDayAppbar(
                        selected = selectedDate,
                        onItemSelected = {
                            selectedDate = it
                        }
                    )
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
            if(transactions.isEmpty()){
                ScreenEmptyState(
                    image = R.drawable.bg_empty_1,
                    title ="No transaction yet",
                    subtitle ="you can add transaction by tapping button + below"
                )
            }else {
                LazyColumn(
                    content = {
                        items(count = 7) { index ->
                            ItemListDailyTransaction() {
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