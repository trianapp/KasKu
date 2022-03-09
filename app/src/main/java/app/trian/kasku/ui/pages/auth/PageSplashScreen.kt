package app.trian.kasku.ui.pages.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.theme.KasKuTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 11.22
 * site https://trian.app
 */

@Composable
fun PageSplashScreen(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit, block = {
        scope.launch {
            delay(1000)
            router.navigate(Routes.ONBOARD)
        }
    })
    //todo
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column {

        }
        Column {

        }
        Column(
            modifier=modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "KasKu")
            Text(text = "Version 0.0.1")
        }
    }
}

@Preview
@Composable
fun PreviewPageSplashScreen() {
    KasKuTheme {
        PageSplashScreen(router = rememberNavController())
    }
}