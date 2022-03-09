package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 20.23
 * site https://trian.app
 */

@Composable
fun AppbarDashboard(
    modifier: Modifier = Modifier,
    title:String="",
    navigationIcon:@Composable ()->Unit={},
    actions:@Composable RowScope.()->Unit={},
    content:@Composable ()->Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(MaterialTheme.colors.surface)
    ) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                navigationIcon.invoke()
            },
            actions = actions,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onBackground
                    )
                )
            }
        )
        Box(modifier = modifier.padding(horizontal = 16.dp)){
            content.invoke()
        }
        Spacer(modifier = modifier.height(10.dp))
    }
}

@Preview
@Composable
fun PreviewAppbarDashboard() {
    KasKuTheme {
        AppbarDashboard(
            title = "Stat"
        )
    }
}