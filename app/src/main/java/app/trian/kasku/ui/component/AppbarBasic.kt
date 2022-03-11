package app.trian.kasku.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 03.15
 * site https://trian.app
 */
@Composable
fun AppbarBasic(
    modifier: Modifier=Modifier,
    title:String="",
    navigationIcon:@Composable (()->Unit)?=null,
    actions:@Composable RowScope.()->Unit={},
) {

    TopAppBar(
        title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier.clip(
            RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ),
        elevation = 0.dp
    )
}

@Preview
@Composable
fun PreviewAppbarBudget() {
    KasKuTheme {
        AppbarBasic(title = "Budget") {

        }
    }
}