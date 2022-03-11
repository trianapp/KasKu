package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.kasku.common.getAppVersion
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.SignOut24

/**
 * NavDrawer
 * author Trian Damai
 * created_at 11/03/22 - 22.59
 * site https://trian.app
 */

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    currentUser: String="",
    onClick: (item: ItemMenuDrawer) -> Unit={},
    onNavigate:(route:String)->Unit={}
) {
    val ctx = LocalContext.current

    val menu = listOf(
        ItemMenuDrawer(
            name = "Categories",
            route = Routes.LIST_CATEGORY,
            type = "nav"
        ),
        ItemMenuDrawer(
            name = "Feedback",
            route = "categories",
            type = "button"
        ),
        ItemMenuDrawer(
            name = "Rating",
            route = "categories",
            type = "button"
        ),
        ItemMenuDrawer(
            name = "Privacy policy",
            route = "categories",
            type = "button"
        ),

    )

    Column(
        modifier=modifier.fillMaxHeight()
            .background(
                MaterialTheme.colors.background
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier=modifier
                .padding(
                    end = 30.dp,
                    start = 30.dp,
                    top = 25.dp
                )
        ) {
            Text(
                text = "Hi!",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            )
            Text(
                text = "Unknown",
                style =  MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            )
        }

        Column {
            menu.forEachIndexed {
                    index,data->
                ItemDrawer(
                    item=data,
                    selected = index ==0,
                    onClick = {
                        if(it.type == "button"){
                            onClick(it)
                        }else{
                            onNavigate(it.route)
                        }
                    }
                )
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick(
                            ItemMenuDrawer(
                                name = "",
                                route = "logout",
                                type = "button"
                            )
                        )
                    }.padding(
                        vertical = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = modifier.width(30.dp))
                Icon(
                    imageVector = Octicons.SignOut24,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onBackground
                    )
                )
            }
            Spacer(modifier = modifier.height(30.dp))
            Text(
                text = "Version ${ctx.getAppVersion()}",
                style=MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground

                ),
                modifier=modifier.padding(
                    vertical = 10.dp,
                    horizontal = 30.dp
                )
            )
        }
    }
}

@Composable
fun ItemDrawer(
    modifier: Modifier=Modifier,
    item:ItemMenuDrawer,
    selected:Boolean=false,
    onClick:(item:ItemMenuDrawer)->Unit={}
) {
    Spacer(modifier = modifier.height(4.dp))
    Row(
        modifier= modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            }.padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(selected){
            Box(modifier = modifier
                .height(30.dp)
                .width(4.dp)
                .background(MaterialTheme.colors.primary)
            )
            Spacer(modifier = modifier.width(26.dp))
        }else{
            Spacer(modifier = modifier.width(30.dp))
        }

        Text(
            text = item.name,
            style = MaterialTheme.typography.button.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onBackground
            )
        )
    }
    Spacer(modifier = modifier.height(10.dp))
}

data class ItemMenuDrawer(
    var name:String,
    var route:String,
    var type:String="link"
)


@Preview
@Composable
fun PreviewNavDrawer() {
    KasKuTheme {
        NavDrawer()
    }
}