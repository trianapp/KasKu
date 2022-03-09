package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.DisableContentColor
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.48
 * site https://trian.app
 */
@Composable
fun AppbarBasic(
    modifier: Modifier=Modifier,
    icon:ImageVector=Octicons.ArrowLeft24,
    title:String="",
    onNavigate:()->Unit={}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .coloredShadow(
                color = DisableContentColor
            )
            .height(70.dp)
            .background(
                MaterialTheme.colors.surface
            )
            .padding(
                horizontal = 30.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconToggleButton(checked = false, onCheckedChange = {
            onNavigate()
        }) {
            Icon(imageVector = icon, contentDescription = "Navigation Icon")
        }
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        )
    }
}