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
import androidx.compose.ui.input.key.Key
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
 * Page Register
 * author Trian Damai
 * created_at 09/03/22 - 11.28
 * site https://trian.app
 */
@Composable
fun PageRegister(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    //register with email and password
    fun processRegister(){
        if(name.isBlank()){
            ctx.toastError(ctx.getString(R.string.name_cannot_empty))
            return
        }
        if(email.isBlank()){
            ctx.toastError(ctx.getString(R.string.email_cannot_empty))
            return
        }
        if(!email.isEmailValid()){
            ctx.toastError(ctx.getString(R.string.email_not_valid))
            return
        }

        if(password.isBlank()){
            ctx.toastError(ctx.getString(R.string.password_cannot_empty))
            return
        }
        authViewModel.registerWithEmailAndPassword(
            name,email,password
        ){
            success,message->
            if(!success){
                ctx.toastError(message)
            }else{
                //verify email then logged in
                ctx.toastSuccess(ctx.getString(R.string.register_success_verify_email))
                router.popBackStack()
            }
        }
    }

    //use google
    val authGoogleLauncher = rememberLauncherForActivityResult(
        contract =GoogleAuthContract(),
        onResult = {
            authViewModel.loginWithGoogle(it){
                success,isNewUser,message->
                if(!success){
                    ctx.toastError(message)
                }else{
                    // goto create bank
                    if(isNewUser){
                        router.navigate(Routes.ADD_BANK){
                            popUpTo(Routes.LOGIN){
                                inclusive = true
                            }
                            launchSingleTop=true
                        }
                    }else{
                        router.navigate(Routes.DASHBOARD){
                            popUpTo(Routes.LOGIN){
                                inclusive = true
                            }
                            launchSingleTop=true
                        }
                    }
                }
            }
        }
    )
    val annotatedSignIn = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.please_read_our))
        }
        append(" ")
        pushStringAnnotation(
            tag = "accept",
            annotation = "accept"
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.privacy_policy))
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.before_continue))
        }
        pop()
    }
    Scaffold(
        topBar = {
            AppbarAuth(
                navigationText = stringResource(R.string.appbar_title_register)
            ){
                router.popBackStack()
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
                    contentDescription = stringResource(R.string.content_description_image_page_register),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.create_your_account),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.subtitle_page_register),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInput(
                    placeholder = stringResource(R.string.placeholder_name),
                    label = stringResource(R.string.label_input_name),
                    singleLine = true,
                    initialValue = name,
                    onChange = {
                        name=it
                    },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                FormInput(
                    placeholder = stringResource(R.string.placeholder_email),
                    label = stringResource(R.string.label_input_email),
                    singleLine = true,
                    initialValue = email,
                    onChange = {
                        email = it
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
                FormInputWithButton(
                    placeholder = stringResource(R.string.placeholder_password),
                    label = stringResource(R.string.label_input_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    initialValue = password,
                    onChange = {
                        password = it
                    },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send
                ){
                    processRegister()
                }
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
                    authGoogleLauncher.launch(AUTH_GOOGLE_CODE)
                }
                Spacer(modifier = modifier.height(20.dp))
                ClickableText(
                    text = annotatedSignIn,
                    onClick = {
                            offset->
                        annotatedSignIn.getStringAnnotations(
                            tag = "accept",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { _ ->


                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageRegister() {
    KasKuTheme {
        PageRegister(router = rememberNavController())
    }
}