package app.trian.kasku.ui.pages.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.ButtonPrimary
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 11.27
 * site https://trian.app
 */
@ExperimentalPagerApi
@Composable
fun PageOnboard(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val pagerState = rememberPagerState(
        pageCount = 3,
        initialPage = 0,
        infiniteLoop = false
    )
    val itemOnboard = listOf(
        PageOnboardModel.FIRST,
        PageOnboardModel.SECOND,
        PageOnboardModel.THIRD,
    )

    var title by remember {
        mutableStateOf(itemOnboard[pagerState.currentPage].title)
    }
    var message by remember {
        mutableStateOf(itemOnboard[pagerState.currentPage].message)
    }

    LaunchedEffect(key1 = Unit, block = {
        snapshotFlow { pagerState.currentPage }
            .collect {
                page->
                title = itemOnboard[page].title
                message= itemOnboard[page].message
            }
    })

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(5.dp))
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 30.dp
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text =stringResource(message),
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
            Spacer(modifier = modifier.height(20.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primary
            )
        }
        HorizontalPager(
            state = pagerState
        ) {
            page->
            Column(
                modifier = modifier.fillMaxHeight(
                    fraction = 0.5f
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = itemOnboard[page].image),
                    contentDescription = stringResource(R.string.content_description_image_page_dashboard),
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction = 0.7f)
                )
            }
        }
        Column(
            modifier = modifier.padding(
                horizontal = 30.dp
            )
        ) {
            ButtonPrimary(stringResource(R.string.btn_get_started)){
                router.navigate(Routes.LOGIN){
                    popUpTo(Routes.ONBOARD){
                        inclusive = true
                    }
                    launchSingleTop=true
                }
            }
        }
        Spacer(modifier = modifier.height(5.dp))
    }
}

sealed class PageOnboardModel(
    var image:Int,
    var title:Int,
    var message:Int
){
    object FIRST:PageOnboardModel(
        image = R.drawable.bg_onboard_1,
        title = R.string.title_onboard_1,
        message = R.string.message_onboard_1
    )
    object SECOND:PageOnboardModel(
        image = R.drawable.bg_onboard_2,
        title = R.string.title_onboard_2,
        message = R.string.message_onboard_2
    )
    object THIRD:PageOnboardModel(
        image = R.drawable.bg_onboard_3,
        title = R.string.title_onboard_3,
        message = R.string.message_onboard_3
    )
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageOnboard() {
    KasKuTheme {
        PageOnboard(router = rememberNavController())
    }
}