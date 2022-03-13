package app.trian.kasku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.R
import app.trian.kasku.ui.theme.DisableColor
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Person16

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 11.36
 * site https://trian.app
 */
@Composable
fun ItemSetting(
    modifier: Modifier=Modifier,
    name:String="",
    description:String="",
    icon:ImageVector=Octicons.Person16,
    actions:@Composable ()->Unit={

    },
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardHeight = currentWidth / 6
    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 30.dp,
                vertical = 6.dp
            )
            .fillMaxWidth()
            .height(cardHeight ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = modifier
                .size(currentWidth / 7)
                .clip(CircleShape)
                .background(DisableColor)
                .padding(
                    all=10.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription ="",
                modifier = modifier
                    .size(cardHeight - cardHeight / 2),
                tint = DisableContentColor
            )
        }
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier= modifier
                .height(cardHeight)
                .width(currentWidth - cardHeight),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.body2.copy(

                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                }
                actions.invoke()
            }
            Divider()
        }

    }
}

@Preview
@Composable
fun PreviewItemSetting() {
    KasKuTheme {
        ItemSetting(
            name = "Yeah",
            description = "yoo"
        )
    }
}