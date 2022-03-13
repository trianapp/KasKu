package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.common.coloredShadow
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 13.55
 * site https://trian.app
 */

@Composable
fun AppbarAuth(
    modifier: Modifier=Modifier,
    navigationText:String="",
    onNavigate:()->Unit={}
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            ))
            .coloredShadow(
                color = DisableContentColor
            )
            .height(70.dp)
            .background(
                MaterialTheme.colors.surface
            ).padding(
                horizontal = 30.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Budget Tracker",
            style = MaterialTheme.typography.h6.copy(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Light
            )
        )
        Text(
            text = navigationText,
            style = MaterialTheme.typography.overline.copy(
                color = MaterialTheme.colors.primary
            ),
            modifier=modifier.padding(all = 4.dp).clickable {
                onNavigate()
            }
        )
    }
}

@Preview
@Composable
fun PreviewAppbarAuth() {
    KasKuTheme {
        AppbarAuth()
    }
}