package app.trian.kasku.ui.pages.transaction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 17.14
 * site https://trian.app
 */
@ExperimentalPagerApi
@Composable
fun PageAddTransaction(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {}) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                title = {
                    Text(
                        text = "Add Transaction",
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    ),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        var pagerState = rememberPagerState(
            initialPage = 0
        )
        HorizontalPager(
            state = pagerState,
            count = 4,
            userScrollEnabled = false
        ) {

        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageAddTransaction() {
    KasKuTheme {
        PageAddTransaction(router = rememberNavController())
    }
}