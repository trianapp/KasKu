package app.trian.kasku.ui.pages.bank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R 
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.common.hideKeyboard
import app.trian.kasku.common.numberKeyboardOption
import app.trian.kasku.common.toastError
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.GradientColor
import app.trian.kasku.ui.theme.HexToJetpackColor
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.listGradient
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import logcat.LogPriority
import logcat.logcat
import kotlin.math.roundToInt

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.28
 * site https://trian.app
 */
@Composable
fun PageAddBank(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val bankViewModel = hiltViewModel<BankViewModel>()

    val currentBank by bankViewModel.currentBankAccount.observeAsState(initial = null)

    var bankName by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    var selectedColor by remember {
        mutableStateOf<GradientColor?>(null)
    }

    fun saveBankAccount(){
        if(bankName.isBlank()){
            ctx.toastError(ctx.getString(R.string.bank_name_cannot_empty))
            return
        }
        if(amount.isBlank()){
            ctx.toastError(ctx.getString(R.string.amount_cannot_empty))
            return
        }
        if(selectedColor == null){
            ctx.toastError(ctx.getString(R.string.color_cannot_empty))
            return
        }

        bankViewModel.saveBank(
            bankName,
            amount.toDouble(),
            selectedColor!!
        ){
            if(it){
                router.navigate(Routes.ADD_BANK_SUCCESS){
                    popUpTo(Routes.ADD_BANK){
                        inclusive= true
                    }
                    launchSingleTop=true
                }
            }else{
                //notify
                ctx.toastError(ctx.getString(R.string.cannot_save_bank_account))
            }
        }
    }
    SideEffect {

        currentBank?.let {
            selectedColor = GradientColor(
                first = it.colorStart,
                second = it.colorEnd
            )
            bankName = it.bankName
            amount = "${it.amount.roundToInt()}"


        }
    }
    LaunchedEffect(key1 = Unit, block = {
        bankViewModel.getCurrentBankAccount()

    })
    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(R.string.appbar_title_create_bank),
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
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_onboard_3),
                    contentDescription = stringResource(R.string.content_description_image_page_bank_account),
                    modifier = Modifier.fillMaxWidth(fraction = 0.4f)
                )
                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.title_page_create_bank_account),
                    style = MaterialTheme.typography.body1
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {


                FormInputPickColor(
                    label = stringResource(R.string.label_select_color),
                    onSelect = {
                        logcat("yoo",LogPriority.ERROR){it.toString()}
                               selectedColor = it
                    },
                    selected = selectedColor
                )
                FormInput(
                    initialValue = bankName,
                    placeholder = stringResource(R.string.placeholder_bank_account_name),
                    label = stringResource(R.string.label_input_bank_account_name),
                    singleLine = true,
                    onChange = {
                        bankName = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                FormInputWithButton(
                    initialValue = amount,
                    placeholder = stringResource(R.string.placeholder_amount),
                    label = stringResource(R.string.label_input_amount),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Send
                    ),
                    maxLength = 13,
                    leading = {
                       Text(
                           text = "Rp",
                           style = MaterialTheme.typography.body1.copy(
                               fontWeight = FontWeight.Bold
                           )
                       )
                    },
                    onChange = {
                        amount = it
                    },
                    onSubmit = {
                        ctx.hideKeyboard()
                        saveBankAccount()
                    },
                    keyboardActions = KeyboardActions(
                        onSend = {
                            ctx.hideKeyboard()
                            saveBankAccount()
                        }
                    )

                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewPageAddBank() {
    KasKuTheme {
        PageAddBank(router = rememberNavController())
    }
}