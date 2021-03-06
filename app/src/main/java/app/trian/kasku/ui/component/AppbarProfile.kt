package app.trian.kasku.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Gear24
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 22.35
 * site https://trian.app
 */

@Composable
fun AppbarProfile(
    onNavigation:()->Unit={},
    onAction:()->Unit={}
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
                IconToggleButton(
                    checked = false,
                    onCheckedChange = {
                        onNavigation()
                    }
                ) {
                    Icon(
                        imageVector = Octicons.Quote24,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground
                    )
                }

        },
        title = {
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
        },
        actions = {
            IconToggleButton(
                checked = false,
                onCheckedChange = {
                    onAction()
                }
            ) {
                Icon(
                    imageVector = Octicons.Gear24,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }

    )
}

@Preview
@Composable
fun PreviewAppBarProfile() {
    KasKuTheme {
        AppbarProfile()
    }
}