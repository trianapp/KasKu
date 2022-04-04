package app.trian.kasku.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import app.trian.kasku.common.toastError
import app.trian.kasku.common.toastSuccess
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 * Page Register
 * author Trian Damai
 * created_at 09/03/22 - 11.28
 * site https://trian.app
 */
@Composable
fun PageChangePassword(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val authViewModel = hiltViewModel<AuthViewModel>()

    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    fun processChangePassword(){
        if(newPassword.isBlank()){
            ctx.toastError(ctx.getString(R.string.password_cannot_empty))
            return
        }

        if(confirmPassword.isBlank()){
            ctx.toastError(ctx.getString(R.string.confirm_password_cannot_empty))
            return
        }

        if(confirmPassword != newPassword){
            ctx.toastError(ctx.getString(R.string.password_not_match))
            return
        }

        authViewModel.changePassword(newPassword){
            success,message->
            if(!success){
                ctx.toastError(message)
            }else{
                ctx.toastSuccess(ctx.getString(R.string.success_change_password))
            }
        }
    }
    Scaffold(
        topBar = {
           AppbarBasic(
               title = stringResource(R.string.appbar_title_change_password),
               navigationIcon = {
                   IconToggleButton(checked = false, onCheckedChange = {
                       router.popBackStack()
                   }) {
                       Icon(imageVector = Octicons.ArrowLeft24, contentDescription = "")
                   }
               }
           ){

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
                    contentDescription = stringResource(R.string.conten_description_image_change_password),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.title_change_password),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.subtitle_page_change_password),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInput(
                    initialValue = newPassword,
                    onChange = {
                               newPassword=it
                    },
                    placeholder = stringResource(R.string.placeholed_new_password),
                    label = stringResource(R.string.labe_input_new_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                )
                FormInputWithButton(
                    initialValue = confirmPassword,
                    onChange = {
                               confirmPassword=it
                    },
                    placeholder = stringResource(R.string.placeholder_confirm_password),
                    label = stringResource(R.string.label_input_confirm_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                ){

                    processChangePassword()
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewPageChangePassword() {
    KasKuTheme {
        PageChangePassword(router = rememberNavController())
    }
}