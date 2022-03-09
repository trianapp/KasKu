package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.00
 * site https://trian.app
 */

@Composable
fun ItemStat(
    modifier: Modifier=Modifier,
    icon:ImageVector=Octicons.ArrowRight24,
    iconColor:Color = MaterialTheme.colors.primary,
    name:String="",
    value:String="",
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = ((currentWidth/2)-40.dp)
    Box(
        modifier = modifier

            .width(cardWidth)
            .height(cardWidth + 30.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
            .clickable {
                onClick()
            }
            .padding(all = 20.dp)

    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = modifier
                    .clip(CircleShape)
                    .background(iconColor)
                    .padding(all = 6.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.subtitle2.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onBackground,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemStat() {
    KasKuTheme {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemStat()
                ItemStat()
            }
        }
    }
}