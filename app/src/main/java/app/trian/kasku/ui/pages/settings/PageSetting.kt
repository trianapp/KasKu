package app.trian.kasku.ui.pages.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.theme.KasKuTheme

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
    //todo
}

@Preview
@Composable
fun PreviewPageSetting() {
    KasKuTheme {
        PageSetting(router = rememberNavController())
    }
}