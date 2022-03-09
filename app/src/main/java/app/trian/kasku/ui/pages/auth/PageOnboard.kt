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
    val pagerState = rememberPagerState(initialPage = 0)
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
            modifier = modifier.fillMaxWidth().padding(
                horizontal = 30.dp
            ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text =title,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text =message,
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
            count = 3,
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
                    contentDescription = "",
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
            ButtonPrimary("Get started"){
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
    var title:String,
    var message:String
){
    object FIRST:PageOnboardModel(
        image = R.drawable.bg_onboard_1,
        title = "Keytar sweenet",
        message = "Portland ugh fashion axe Halvetica. Echo Park Austin gastropub roof party"
    )
    object SECOND:PageOnboardModel(
        image = R.drawable.bg_onboard_2,
        title = "Synth polaroid",
        message = "In the tumultuous of cutting-in and attending to whole there"
    )
    object THIRD:PageOnboardModel(
        image = R.drawable.bg_onboard_3,
        title = "Retro Occupy",
        message = "Being the savage's bawsman, that is, the person who pulled the bow-oar in."
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