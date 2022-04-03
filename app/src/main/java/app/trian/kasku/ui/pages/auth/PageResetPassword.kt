package app.trian.kasku.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.hideKeyboard
import app.trian.kasku.common.isEmailValid
import app.trian.kasku.common.toastError
import app.trian.kasku.common.toastSuccess
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.AppbarAuth
import app.trian.kasku.ui.component.ButtonSocial
import app.trian.kasku.ui.component.FormInput
import app.trian.kasku.ui.component.FormInputWithButton
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 11.27
 * site https://trian.app
 */
@Composable
fun PageResetPassword(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()

    var email by remember {
        mutableStateOf("")
    }
    fun processResetPassword(){
        if(email.isBlank()){
            ctx.toastError(ctx.getString(R.string.email_cannot_empty))
            return
        }
        if(!email.isEmailValid()){
            ctx.toastError(ctx.getString(R.string.email_not_valid))
            return
        }
        authViewModel.resetPasswordWithEmail(email){
            success,message->
            if(!success){
                ctx.toastError(message)
            }else{
                ctx.toastSuccess(
                    ctx.getString(R.string.email_has_sended,email)
                )
            }
        }
    }
    Scaffold(
        topBar = {
            AppbarAuth(navigationText = stringResource(R.string.btn_login)){
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
                    contentDescription = stringResource(R.string.content_description_change_password),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.title_reset_password),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.subtitle_reset_password),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInputWithButton(
                    placeholder = stringResource(R.string.placeholder_email),
                    label = stringResource(R.string.label_input_email),
                    singleLine = true,
                    onSubmit = {
                        processResetPassword()
                    },
                    keyboardType = KeyboardType.Email,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageResetPassword() {
    KasKuTheme {
        PageResetPassword(router = rememberNavController())
    }
}