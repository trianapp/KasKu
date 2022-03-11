package app.trian.kasku.ui.pages.transaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.common.numberKeyboardOption
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.ExpensesColor
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var scope = rememberCoroutineScope()

    var pagerState = rememberPagerState(
        initialPage = 0
    )
    var transactionType by remember {
        mutableStateOf(BudgetType.INCOME)
    }
    val pageCount = 6



    fun nextPage(){
        scope.launch {
            pagerState.scrollToPage(pagerState.currentPage + 1)
        }

    }
    fun prevPage(){
        scope.launch {
            if(pagerState.currentPage > 0){
                pagerState.scrollToPage(pagerState.currentPage-1)
            }else{
                router.popBackStack()
            }
        }

    }

    //handle system back pressed
    BackHandler {
            prevPage()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                               prevPage()
                        }
                    ) {
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
        backgroundColor = BackgroundDashboard
    ) {

        HorizontalPager(
            state = pagerState,
            count = pageCount,
            userScrollEnabled = false
        ) {
            /**
             * page:
             * 0 make decision expense or income
             * 1 input transaction title/name
             * 2 select budget
             * 3 select category
             * 4 input amount
             * 5 input date
             * */

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                when (pagerState.currentPage) {
                    0 -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(
                                    vertical = 30.dp,
                                    horizontal = 30.dp
                                ),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.bg_onboard_3),
                                    contentDescription = "",
                                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                                )
                            }
                            Text(
                                text = "What kind of transaction it is?",
                                style = MaterialTheme.typography.h4.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onBackground
                                ),
                                modifier = modifier
                                    .fillMaxWidth(fraction = 0.8f)
                            )
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ItemStat(
                                    name = "Income",
                                    iconColor = MaterialTheme.colors.secondary
                                ){
                                    transactionType = BudgetType.INCOME
                                    nextPage()

                                }
                                ItemStat(
                                    name = "Expense",
                                    iconColor = MaterialTheme.colors.primary
                                ){

                                    transactionType = BudgetType.EXPENSE
                                    nextPage()
                                }
                            }
                        }

                    }
                    1 -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(
                                    vertical = 30.dp,
                                    horizontal = 30.dp
                                ),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column {
                                ItemAddTransaction(
                                    budgetType = transactionType,
                                    amount = ""
                                )

                            }
                            Column {
                                FormInputWithButton(
                                    label = "payee name",
                                    placeholder = "Enter payee name"
                                ){
                                   nextPage()
                                }
                            }
                        }
                    }
                    2 -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(
                                        vertical = 30.dp,
                                        horizontal = 30.dp
                                    )
                            ) {

                                ItemAddTransaction(
                                    budgetType = transactionType,
                                    amount = ""
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Payee name",
                                    value = "Motorbike engine oil"
                                )
                            }
                            Column(
                                modifier = modifier.padding(
                                    bottom = 30.dp
                                )
                            ) {
                                Text(
                                    text = "Choose Budget",
                                    style = MaterialTheme.typography.h4.copy(
                                        color=MaterialTheme.colors.onBackground,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier.padding(
                                        horizontal = 30.dp
                                    )
                                )
                                LazyRow(content = {
                                    items(count = 4){
                                        ItemCategory(
                                            name = "Tabungan",
                                        ){
                                            nextPage()
                                        }
                                    }
                                })
                            }
                        }
                    }
                    3 -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(
                                        vertical = 30.dp,
                                        horizontal = 30.dp
                                    )
                            ) {
                                ItemAddTransaction(
                                    budgetType = transactionType,
                                    amount = ""
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Payee name",
                                    value = "Motorbike engine oil"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Budget name",
                                    value = "Tabungan"
                                )

                            }
                            Column(
                                modifier = modifier.padding(
                                    bottom = 30.dp
                                )
                            ) {
                                Text(
                                    text = "Choose Category",
                                    style = MaterialTheme.typography.h4.copy(
                                        color=MaterialTheme.colors.onBackground,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier.padding(
                                        horizontal = 30.dp
                                    )
                                )
                                LazyRow(content = {
                                    items(count = 4){
                                        ItemCategory(
                                            name = "Bank"
                                        ){
                                            nextPage()
                                        }
                                    }
                                })
                            }
                        }
                    }
                    4 -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(
                                    vertical = 30.dp,
                                    horizontal = 30.dp
                                ),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = modifier

                            ) {
                                ItemAddTransaction(
                                    budgetType = transactionType,
                                    amount = ""
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Payee name",
                                    value = "Motorbike engine oil"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Budget name",
                                    value = "Tabungan"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Category",
                                    value = "Bank"
                                )

                            }
                            Column {
                                FormInputWithButton(
                                    label = "Amount",
                                    placeholder = "0",
                                    leading = {
                                        Text(
                                            text = "Rp",
                                            style = MaterialTheme.typography.body1.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colors.onBackground
                                            )
                                        )
                                    },
                                    masked = CurrencyTransformation("#.###.###.###.###"),
                                    maxLength = 13,
                                    singleLine = true,
                                    keyboardOptions = numberKeyboardOption
                                ){
                                    nextPage()
                                }
                            }
                        }
                    }
                    5 -> {

                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(
                                    vertical = 30.dp,
                                    horizontal = 30.dp
                                ),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = modifier

                            ) {
                                ItemAddTransaction(
                                    budgetType = transactionType,
                                    amountName = "Amount",
                                    amount = "1.000.000.000"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Payee name",
                                    value = "Motorbike engine oil"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Budget name",
                                    value = "Tabungan"
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                                ItemAddTransaction(
                                    name = "Category",
                                    value = "Bank"
                                )

                            }
                            Column {
                                FormInputWithButton(
                                    label = "Amount",
                                    placeholder = "Select date",
                                    singleLine = true
                                ){
                                    scope.launch {
                                        router.navigate(Routes.ADD_TRANSACTION_SUCCESS){
                                            popUpTo(Routes.ADD_TRANSACTION){
                                                inclusive=true
                                            }
                                            launchSingleTop = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
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