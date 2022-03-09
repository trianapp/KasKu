package app.trian.kasku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.pages.auth.PageLogin
import app.trian.kasku.ui.pages.auth.PageOnboard
import app.trian.kasku.ui.pages.auth.PageRegister
import app.trian.kasku.ui.pages.auth.PageSplashScreen
import app.trian.kasku.ui.pages.bank.PageAddBank
import app.trian.kasku.ui.pages.bank.PageAddBankSuccess
import app.trian.kasku.ui.pages.dashboard.PageHome
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import logcat.LogPriority
import logcat.logcat

@ExperimentalPagerApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KasKuTheme {
                val router = rememberAnimatedNavController()
                val systemUI = rememberSystemUiController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                   AnimatedNavHost(
                       navController = router,
                       startDestination = Routes.SPLASH
                   ){
                       composable(Routes.SPLASH){
                           val uiColor = MaterialTheme.colors.primary
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = false
                           )
                           PageSplashScreen(router = router)
                       }
                       composable(Routes.ONBOARD){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageOnboard(router = router)
                       }
                       composable(Routes.LOGIN){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageLogin(router = router)
                       }
                       composable(Routes.REGISTER){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageRegister(router = router)
                       }
                       composable(Routes.ADD_BANK){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageAddBank(router = router)
                       }
                       composable(Routes.ADD_BANK_SUCCESS){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageAddBankSuccess(router = router)
                       }
                       navigation(
                           route = Routes.DASHBOARD,
                           startDestination = Routes.Dashboard.HOME
                       ){

                           composable(Routes.Dashboard.HOME){
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )
                               PageHome(router = router)
                           }
                           composable(Routes.Dashboard.DAILY){
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )

                           }
                           composable(Routes.Dashboard.BUDGET){
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )

                           }
                           composable(Routes.Dashboard.PROFILE){
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )

                           }
                       }
                   }
                }
            }
        }
    }
}

