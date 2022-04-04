package app.trian.kasku.ui.pages.transaction

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.domain.BudgetType
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.component.calendar.DialogCalendarPicker
import app.trian.kasku.ui.component.transaction.ScreenNameTransaction
import app.trian.kasku.ui.component.transaction.ScreenPickTypeTransaction
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 17.14
 * site https://trian.app
 */
@SuppressLint("MutableCollectionMutableState")
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun PageAddTransaction(
    modifier: Modifier = Modifier,
    categories:List<Category> = listOf(),
    router: NavHostController,
    onSaveTransaction:()->Unit={}
) {
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = 5,
        initialPage = 0,
        infiniteLoop = false
    )
    var transactionType by remember {
        mutableStateOf(BudgetType.INCOME)
    }
    var transactionName by remember {
        mutableStateOf("")
    }
    var categoryName by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    var transactionDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    var dialogCalendarPicker by remember {
        mutableStateOf(false)
    }

    var itemsDetails by remember {
        mutableStateOf<MutableList<ItemDetailTransactionModel>>(mutableListOf())
    }


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

    //pick calendar
    DialogCalendarPicker(
        show = dialogCalendarPicker,

        onDismiss = {
            dialogCalendarPicker = false
        },
        onDateSelected = {
            dialogCalendarPicker = false
        }
    )

    //handle system back pressed
    BackHandler {
            prevPage()
    }
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Add transaction",
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            router.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
        backgroundColor = BackgroundDashboard
    ) {

        HorizontalPager(
            state = pagerState,
            dragEnabled = false
        ) {
            page->
            /**
             * page:
             * 0 make decision expense or income
             * 1 input transaction title/name
             * 2 select category
             * 3 input amount
             * 4 input date
             * */

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {

                AnimatedVisibility(
                    visible = page== pagerState.currentPage,
                    enter = slideInHorizontally(
                        initialOffsetX = { it }, // it == fullWidth
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearEasing
                        )
                    ),

                ) {
                    when (page) {
                        0 -> {
                            ScreenPickTypeTransaction(
                                text = "What kind of transaction it is?",
                                onExpenses = {
                                    if(itemsDetails.size == 0) {
                                        itemsDetails += ItemDetailTransactionModel(
                                            label = "transaction type",
                                            value = "Expense"
                                        )
                                    }else{
                                        itemsDetails +=  itemsDetails[0].apply {
                                            value = "Expense"
                                        }
                                    }
                                    transactionType = BudgetType.EXPENSE
                                    nextPage()
                                },
                                onIncome = {
                                    if(itemsDetails.size == 0) {
                                        itemsDetails += ItemDetailTransactionModel(
                                            label = "transaction type",
                                            value = "Income"
                                        )
                                    }else{
                                        itemsDetails +=  itemsDetails[0].apply {
                                            value = "Income"
                                        }
                                    }
                                    transactionType = BudgetType.INCOME
                                    nextPage()
                                }
                            )

                        }
                        1 -> {
                            ScreenNameTransaction(
                                itemModels = itemsDetails,
                                inputContent = {
                                    FormInputWithButton(
                                        label = "payee name",
                                        placeholder = "Enter payee name",
                                        initialValue = transactionName,
                                        onChange = {
                                            transactionName = it
                                        }
                                    ){

                                        if(itemsDetails.size >= 2) {
                                            itemsDetails[1] = itemsDetails[1].apply {
                                                value=transactionName
                                            }
                                        }else{
                                            itemsDetails += ItemDetailTransactionModel(
                                                label = "payee name",
                                                value = transactionName
                                            )
                                        }
                                        nextPage()
                                    }
                                }
                            )
                        }
                        2 -> {
                            ScreenNameTransaction(
                                itemModels = itemsDetails,
                                inputContent = {
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
                                        item {
                                            ItemAddSelectionBudgetAndCategory()
                                        }
                                        items(count = 4){
                                            ItemSelectionBudgetAndCategory(
                                                name = "Bank",
                                                selected = false
                                            ){
                                                if(itemsDetails.size >= 3) {
                                                    itemsDetails[2] = itemsDetails[2].apply {
                                                        value = "Bank"
                                                    }

                                                }else{
                                                    itemsDetails += ItemDetailTransactionModel(
                                                        label = "category",
                                                        value = "Cash"
                                                    )
                                                }
                                                nextPage()
                                            }
                                        }
                                    })
                                }
                            )

                        }
                        3 -> {
                            ScreenNameTransaction(
                                itemModels = itemsDetails,
                                inputContent = {
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
                                        keyboardType = KeyboardType.Number,
                                        initialValue = amount,
                                        onChange = {
                                            amount = it
                                        }
                                    ){
                                        itemsDetails[0] = itemsDetails[0].apply {
                                            isWithAmount = true
                                            amountValue = amount
                                            amountLabel = "amount"
                                        }
                                        nextPage()
                                    }
                                }
                            )
                        }
                        4 -> {
                            ScreenNameTransaction(
                                itemModels = itemsDetails,
                                inputContent = {
                                    FormPickerWithButton(
                                        label = "Date",
                                        placeholder = "Select date",
                                        onClick = {
                                            dialogCalendarPicker = true
                                        },
                                        onSubmit = {
                                            scope.launch {
                                                router.navigate(Routes.ADD_TRANSACTION_SUCCESS){
                                                    popUpTo(Routes.ADD_TRANSACTION){
                                                        inclusive=true
                                                    }
                                                    launchSingleTop = true
                                                }
                                            }
                                        }
                                    )
                                }
                            )
                        }
                        else -> {

                        }
                    }
                }


            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageAddTransaction() {
    KasKuTheme {
        PageAddTransaction(
            router = rememberNavController()
        )
    }
}