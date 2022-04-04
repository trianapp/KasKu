package app.trian.kasku.ui.pages.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.*
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarAuth
import app.trian.kasku.ui.component.ButtonSocial
import app.trian.kasku.ui.component.FormInput
import app.trian.kasku.ui.component.FormInputWithButton
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.fontFamily

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 11.27
 * site https://trian.app
 */
@Composable
fun PageLogin(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    //login with email and password
    fun processLogin(){
        if(email.isBlank()){
            ctx.toastError(ctx.getString(R.string.email_cannot_empty))
            return
        }
        if(password.isBlank()){
            ctx.toastError(ctx.getString(R.string.password_cannot_empty))
            return
        }
        if(!email.isEmailValid()){
            ctx.toastError(ctx.getString(R.string.email_not_valid))
            return
        }

        authViewModel.loginWithEmailAndPassword(email, password){
            success,isNewUser,message->
            if(!success){
                ctx.toastError(message)
            }else{
                //check bank account
                if(isNewUser){
                    router.navigate(Routes.ADD_BANK){
                        popUpTo(Routes.LOGIN){
                            inclusive=true
                        }
                        launchSingleTop=true
                    }
                }else{
                    router.navigate(Routes.DASHBOARD){
                        popUpTo(Routes.LOGIN){
                            inclusive=true
                        }
                        launchSingleTop=true
                    }
                }

            }
        }
    }

    //login with google
    val googleAuthLauncher = rememberLauncherForActivityResult(
        contract = GoogleAuthContract(),
        onResult ={
            authViewModel.loginWithGoogle(it){
                success,isNewUser,message->
                if(!success){
                    ctx.toastError(message)
                }else{
                    //:check bank account
                    if(isNewUser){
                        router.navigate(Routes.ADD_BANK){
                            popUpTo(Routes.LOGIN){
                                inclusive=true
                            }
                            launchSingleTop=true
                        }
                    }else{
                        router.navigate(Routes.DASHBOARD){
                            popUpTo(Routes.LOGIN){
                                inclusive=true
                            }
                            launchSingleTop=true
                        }
                    }
                }
            }
        }
    )

    //annotation for text forget password
    val annotationStringForgotPassword = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.forgot_password))
        }
        append(" ")
        pushStringAnnotation(
            tag = "reset_here",
            annotation = "reset_here"
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.reset_here))
        }
        pop()
    }

    Scaffold(
        topBar = {
            AppbarAuth(navigationText = stringResource(R.string.btn_sign_up)){
                router.navigate(Routes.REGISTER)
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.bg_onboard_2),
                    contentDescription = stringResource(R.string.content_description_imaeg_page_login),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.login_to_your_account),
                    style = MaterialTheme.typography.body1
                )
                FormInput(
                    initialValue = email,
                    onChange = {
                               email=it
                    },
                    placeholder = stringResource(R.string.placeholder_email),
                    label = stringResource(R.string.label_input_email),
                    singleLine = true,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
                FormInputWithButton(
                    initialValue = password,
                    onChange = {
                               password = it
                    },
                    placeholder = stringResource(R.string.placeholder_password),
                    label = stringResource(R.string.label_input_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    onSubmit = {
                        processLogin()
                    },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send
                )
            }
            Text(
                text = stringResource(R.string.or),
                style = MaterialTheme.typography.caption
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonSocial(stringResource(R.string.btn_login_with_google)){
                    googleAuthLauncher.launch(AUTH_GOOGLE_CODE)
                }
                Spacer(modifier = modifier.height(20.dp))
                ClickableText(
                    text = annotationStringForgotPassword,
                    onClick = {
                            offset->
                        annotationStringForgotPassword.getStringAnnotations(
                            tag = "reset_here",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { _ ->
                            router.navigate(Routes.RESET_PASSWORD)

                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageLogin() {
    KasKuTheme {
        PageLogin(router = rememberNavController())
    }
}