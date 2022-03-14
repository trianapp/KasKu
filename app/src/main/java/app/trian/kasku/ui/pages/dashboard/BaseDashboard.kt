package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.KasKuBottomNavigation
import app.trian.kasku.ui.component.NavDrawer
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Plus24
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 17.07
 * site https://trian.app
 */

@ExperimentalMaterialApi
@Composable
fun PageBaseDashboard(
    dashboardViewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
    router: NavHostController,
    drawerState: DrawerState,
    topAppbar: @Composable ()->Unit = {},
    onRestartActivity:()->Unit={},
    content:@Composable ()->Unit={}
) {

    val scope = rememberCoroutineScope()
    ModalDrawer(
        drawerState = drawerState ,
        drawerContent = {
            NavDrawer(
                onClick = {

                    scope.launch {
                        drawerState.close()
                        delay(400)
                        when(it.route){
                            "logout"->{
                                dashboardViewModel.signOut {
                                    onRestartActivity()
                                }
                            }
                        }

                    }
                },
                onNavigate = {
                    scope.launch {
                        drawerState.close()
                        delay(400)
                        router.navigate(it)
                    }
                }
            )
        },
        drawerShape = RectangleShape,
        drawerElevation = 0.dp,
        scrimColor = MaterialTheme.colors.primary.copy(
            alpha = 0.3f
        )
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    cutoutShape = CircleShape,
                    elevation=0.dp,
                    backgroundColor = BackgroundDashboard,
                ) {
                    KasKuBottomNavigation(router = router)
                }
            },
            topBar = {
                topAppbar.invoke()
            },
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        router.navigate(Routes.ADD_TRANSACTION)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Octicons.Plus24,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
            backgroundColor= BackgroundDashboard,
            floatingActionButtonPosition = FabPosition.Center
        ) {

            content.invoke()

        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewBaseDashboard() {
    KasKuTheme {
        PageBaseDashboard(
            dashboardViewModel = hiltViewModel<DashboardViewModel>(),
            router = rememberNavController(),
            drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    }
}