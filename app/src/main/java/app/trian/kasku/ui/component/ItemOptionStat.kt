package app.trian.kasku.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.R
import app.trian.kasku.ui.theme.DisableColor
import app.trian.kasku.ui.theme.DisableContentColor

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 02.51
 * site https://trian.app
 */
@Composable
fun ItemOptionStat(
    modifier: Modifier=Modifier,
    name:String="",
    icon:Int=R.drawable.ic_category_hotel,
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
            .height(cardWidth -20.dp)
            .clip(MaterialTheme.shapes.large)
            .coloredShadow(
                color = DisableContentColor,
                alpha = 1f
            )
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
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(DisableColor)
                    .padding(all = 4.dp)
            ) {
                Image(
                    painter = painterResource(id =icon),
                    contentDescription = "",
                    modifier = modifier.align(Alignment.Center)
                )
            }
            Text(
                text =name ,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}

@Preview
@Composable
fun PreviewItemOptionStat() {
    KasKuTheme {
        ItemOptionStat(
            name = "Export"
        )
    }
}