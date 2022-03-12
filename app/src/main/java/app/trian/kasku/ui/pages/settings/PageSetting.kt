package app.trian.kasku.ui.pages.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.getAppVersion
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarBasic
import app.trian.kasku.ui.component.ButtonSmallSecondary
import app.trian.kasku.ui.component.ItemSetting
import app.trian.kasku.ui.component.coloredShadow
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Info24
import compose.icons.octicons.Key24
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 01.37
 * site https://trian.app
 */
@Composable
fun PageSetting(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val itemsSetting = listOf(
        SettingModel(
            name = "Change password",
            description="Change your password",
            icon = Octicons.Key24,
            action = "switch",
            route = ""
        ),
        SettingModel(
            name = "About app",
            description="More about app",
            icon = Octicons.Info24,
            action = "version",
            route = Routes.CHANGE_PASSWORD
        )
    )

    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Settings",
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            router.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
    ) {
        LazyColumn(content = {
            item {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 30.dp
                        )
                ) {

                    Column(
                        modifier = modifier.padding(top = 20.dp)
                    ) {
                        Row(modifier = modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colors.primary)
                            .coloredShadow(
                                MaterialTheme.colors.primary
                            )
                            .padding(all = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Do more with premium",
                                    style = MaterialTheme.typography.caption.copy(
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                )
                                Spacer(modifier = modifier.height(6.dp))
                                Text(
                                    text = "Become premium",
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                )
                            }
                        }
                    }
                    Image(
                        painter = painterResource(id =R.drawable.ic_premium),
                        contentDescription = "",
                        modifier = modifier
                            .align(Alignment.TopEnd)
                            .size(50.dp)
                    )
                }
            }
            item {
                Spacer(modifier = modifier.height(30.dp))
            }
            items(itemsSetting){
                menu->
                ItemSetting(
                    name = menu.name,
                    description=menu.description,
                    icon=menu.icon,
                    actions = {
                        if(menu.action == "switch"){

                        }else if(menu.action == "version"){
                            Text(
                                text = ctx.getAppVersion(),
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onSurface
                                )
                            )
                        }
                    },
                    onClick = {
                        if(menu.type == "nav"){
                            router.navigate(menu.route)
                        }
                    }
                )

            }
        })
    }
}

data class SettingModel(
    var name:String,
    var description:String,
    var icon:ImageVector,
    var action:String,
    var type:String="nav",
    var route:String=""
)

@Preview
@Composable
fun PreviewPageSetting() {
    KasKuTheme {
        PageSetting(router = rememberNavController())
    }
}