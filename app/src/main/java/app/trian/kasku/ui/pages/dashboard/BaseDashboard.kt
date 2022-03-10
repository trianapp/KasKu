package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.KasKuBottomNavigation
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Plus24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 17.07
 * site https://trian.app
 */

@Composable
fun PageBaseDashboard(
    modifier: Modifier = Modifier,
    router: NavHostController,
    topAppbar: @Composable ()->Unit = {},
    content:@Composable ()->Unit={}
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

@Preview
@Composable
fun PreviewBaseDashboard() {
    KasKuTheme {
        PageBaseDashboard(router = rememberNavController())
    }
}