package app.trian.kasku.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.toastError
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme

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
        if(newPassword.isNotBlank() && confirmPassword.isNotBlank()){

            if(newPassword != confirmPassword){
                ctx.toastError(ctx.getString(R.string.password_not_match))
                return
            }
            authViewModel.changePassword(newPassword){
                succse,message->

            }
        }else{
            ctx.toastError(ctx.getString(R.string.password_cannot_blank))
        }
    }
    Scaffold(
        topBar = {
           AppbarBasic(title = "Change password"){
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
                    contentDescription = "",
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Change password",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Keep your account more save with change periodically",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInput(
                    initialValue = newPassword,
                    onChange = {
                               newPassword=it
                    },
                    placeholder = "your new password",
                    label = "New password",
                    showPasswordObsecure = true,
                    singleLine = true
                )
                FormInputWithButton(
                    initialValue = confirmPassword,
                    onChange = {
                               confirmPassword=it
                    },
                    placeholder = "confirm new password",
                    label = "Confirm password",
                    showPasswordObsecure = true,
                    singleLine = true
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