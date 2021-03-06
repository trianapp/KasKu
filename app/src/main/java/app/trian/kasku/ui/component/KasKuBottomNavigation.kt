package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.theme.DisableColor
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.*

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 19.01
 * site https://trian.app
 */

@Composable
fun KasKuBottomNavigation(
    router:NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by router.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val items = listOf(
        BottomNavigationModel.Home,
        BottomNavigationModel.Daily,
        BottomNavigationModel.Center,
        BottomNavigationModel.Budget,
        BottomNavigationModel.Profile
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed {
                index,menu ->
            if(index == 2){
                Spacer(modifier = modifier.width(50.dp))
            }else{
                Column(
                    modifier = modifier
                        .width(currentWidth / 6)
                        .padding(
                            vertical = 4.dp
                        )
                        .clickable {
                            if (menu.route != "") {
                                router.navigate(menu.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = menu.icon,
                        contentDescription = "",
                        tint = if(currentRoute?.route == menu.route) MaterialTheme.colors.primary else DisableContentColor
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = menu.title,
                        style = MaterialTheme.typography.overline.copy(
                            color = if(currentRoute?.route == menu.route) MaterialTheme.colors.primary else DisableContentColor
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                }
            }
        }
    }


}

sealed class BottomNavigationModel(
    var icon:ImageVector,
    var title:String,
    var route:String
){
    object Daily:BottomNavigationModel(
        icon = Octicons.Calendar16,
        title = "Daily",
        route = Routes.Dashboard.DAILY
    )
    object Home:BottomNavigationModel(
        icon = Octicons.Project16,
        title = "Home",
        route = Routes.Dashboard.HOME
    )
    object Center:BottomNavigationModel(
        icon = Octicons.Plus16,
        title = "",
        route = ""
    )
    object Budget:BottomNavigationModel(
        icon = Octicons.Flame16,
        title = "Budget",
        route = Routes.Dashboard.BUDGET
    )
    object Profile:BottomNavigationModel(
        icon = Octicons.Person16,
        title = "Profile",
        route = Routes.Dashboard.PROFILE
    )
}

@Preview
@Composable
fun BottomNavigationPreview() {
    KasKuTheme {
        KasKuBottomNavigation(router = rememberNavController())
    }
}