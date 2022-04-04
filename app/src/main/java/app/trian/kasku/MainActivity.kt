package app.trian.kasku

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import app.trian.kasku.common.toastSuccess
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.pages.auth.*
import app.trian.kasku.ui.pages.budget.BudgetViewModel
import app.trian.kasku.ui.pages.wallet.PageAddWallet
import app.trian.kasku.ui.pages.wallet.PageAddWalletSuccess
import app.trian.kasku.ui.pages.budget.PageCreateBudget
import app.trian.kasku.ui.pages.category.CategoryViewModel
import app.trian.kasku.ui.pages.category.PageAddCategory
import app.trian.kasku.ui.pages.category.PageListCategory
import app.trian.kasku.ui.pages.dashboard.PageBudget
import app.trian.kasku.ui.pages.dashboard.PageDaily
import app.trian.kasku.ui.pages.dashboard.PageHome
import app.trian.kasku.ui.pages.dashboard.PageProfile
import app.trian.kasku.ui.pages.settings.PageSetting
import app.trian.kasku.ui.pages.stat.PageStat
import app.trian.kasku.ui.pages.transaction.PageAddTransaction
import app.trian.kasku.ui.pages.transaction.PageAddTransactionSuccess
import app.trian.kasku.ui.pages.transaction.PageDetailTransaction
import app.trian.kasku.ui.pages.transaction.TransactionViewModel
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
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
                val ctx = LocalContext.current

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
                       composable(
                           Routes.ONBOARD,
                           enterTransition = { fadeIn() },
                           exitTransition = { fadeOut() }
                       ){
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
                       composable(Routes.CHANGE_PASSWORD){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageChangePassword(router = router)
                       }
                       composable(Routes.RESET_PASSWORD){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageResetPassword(router = router)
                       }

                       composable(Routes.ADD_BANK){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageAddWallet(router = router)
                       }
                       composable(
                           Routes.ADD_BANK_SUCCESS,
                           enterTransition = { slideInVertically { it } },
                           exitTransition = { slideOutVertically { it } }
                       ){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageAddWalletSuccess(router = router)
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
                               PageHome(
                                   router = router,
                                   onRestartActivity = ::restartActivity
                               )
                           }
                           composable(Routes.Dashboard.DAILY){
                               //theme
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )

                               //data
                               val transactionViewModel = hiltViewModel<TransactionViewModel>()
                               val listTransaction by transactionViewModel.listTransaction.observeAsState(
                                   initial = emptyList()
                               )

                               //lifecycle
                               PageDaily(
                                   transactions=listTransaction,
                                   router = router,
                                   onRestartActivity = ::restartActivity
                               )

                           }
                           composable(Routes.Dashboard.BUDGET){
                               //theme
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )
                               //data
                               val budgetViewModel = hiltViewModel<BudgetViewModel>()
                               val listBudget by budgetViewModel.listBudget.observeAsState(
                                   initial = emptyList()
                               )

                               //lifecycle
                               LaunchedEffect(key1 = Unit, block ={
                                   budgetViewModel.getListBudget()
                               })

                               PageBudget(
                                   budgets=listBudget,
                                   router = router,
                                   onRestartActivity = ::restartActivity
                               )
                           }
                           composable(Routes.Dashboard.PROFILE){
                               val uiColor = MaterialTheme.colors.surface
                               systemUI.setSystemBarsColor(
                                   color = uiColor,
                                   darkIcons = true
                               )
                               PageProfile(
                                   router = router,
                                   onRestartActivity = ::restartActivity
                               )

                           }
                       }
                       composable(Routes.STAT_EXPENSE){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageStat(
                               router = router,
                               type = BudgetType.EXPENSE
                           )
                       }
                       composable(Routes.STAT_INCOME){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageStat(
                               router = router,
                               type = BudgetType.INCOME
                           )
                       }
                       composable(Routes.CREATE_BUDGET){
                           //theme and style
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )

                           //data
                           val budgetViewModel = hiltViewModel<BudgetViewModel>()
                           val listCategory by budgetViewModel.listCategory.observeAsState(
                               initial = emptyList()
                           )

                           LaunchedEffect(
                               key1 = Unit,
                               block ={
                                   budgetViewModel.getListCategory()
                               }
                           )

                           PageCreateBudget(
                               categories=listCategory,
                               router = router,
                               onSubmit = {
                                   categoryId, budgetName, budgetDescription,budgetAmount ->
                                   budgetViewModel.saveBudget(
                                       categoryId,
                                       budgetName,
                                       budgetDescription,
                                       budgetAmount
                                   ){
                                       ctx.toastSuccess("New budget has been saved!")

                                       router.popBackStack()
                                   }
                               }
                           )
                       }
                       composable(Routes.DETAIL_TRANSACTION){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageDetailTransaction(router = router)
                       }
                       composable(
                           Routes.ADD_TRANSACTION,
                           enterTransition = { slideInVertically { it } },
                           exitTransition = { slideOutVertically { it } }
                       ){
                           //change theme
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           //data and viewmodel
                           val transactionViewModel = hiltViewModel<TransactionViewModel>()
                           val listCategory by transactionViewModel.listCategory.observeAsState(
                               initial = emptyList()
                           )

                           //lifecycle
                           LaunchedEffect(key1 = Unit, block = {
                               transactionViewModel.getListCategory()
                           })
                           //page
                           PageAddTransaction(
                               router = router,
                               categories = listCategory,
                               onSaveTransaction = {

                               }
                           )
                       }
                       composable(
                           Routes.ADD_TRANSACTION_SUCCESS,
                           enterTransition = { slideInVertically { it } },
                           exitTransition = { slideOutVertically { it } }
                       ){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageAddTransactionSuccess(router = router)
                       }
                       composable(Routes.LIST_CATEGORY){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageListCategory(router = router)
                       }
                       composable(Routes.ADD_CATEGORY){
                           //theme
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           //data
                           val categoryViewModel = hiltViewModel<CategoryViewModel>()
                           //lifecycle
                           PageAddCategory(
                               router = router,
                               onSubmit = {
                                   categoryName, icon ->
                                   categoryViewModel.saveCategory(categoryName,icon){
                                       ctx.toastSuccess("Category has ben saved")
                                       router.popBackStack()
                                   }
                               }
                           )
                       }
                       composable(Routes.SETTINGS){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageSetting(router = router)
                       }
                       composable(Routes.UPDATE_PROFILE){
                           val uiColor = MaterialTheme.colors.surface
                           systemUI.setSystemBarsColor(
                               color = uiColor,
                               darkIcons = true
                           )
                           PageUpdateProfile(router = router)
                       }
                   }
                }
            }
        }
    }

    private fun restartActivity(){
        runOnUiThread {
            Intent(this,MainActivity::class.java).apply {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.also {
                startActivity(it)
                finish()
            }
        }
    }
}

