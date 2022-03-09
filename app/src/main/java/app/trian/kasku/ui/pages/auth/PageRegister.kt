package app.trian.kasku.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.ui.component.AppbarAuth
import app.trian.kasku.ui.component.ButtonSocial
import app.trian.kasku.ui.component.FormInput
import app.trian.kasku.ui.component.FormInputWithButton
import app.trian.kasku.ui.theme.KasKuTheme

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
    Scaffold(
        topBar = {
            AppbarAuth(
                navigationText = "Login"
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
                    painter = painterResource(id = R.drawable.bg_onboard_3),
                    contentDescription = ""
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Login to your account",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Keep your financial data store to our server so that you can access from anywhere you want",
                    style = MaterialTheme.typography.caption
                )
                FormInput(
                    placeholder = "Your name",
                    label = "Full name",
                    singleLine = true
                )
                FormInput(
                    placeholder = "name@trian.app",
                    label = "Email",
                    singleLine = true
                )
                FormInputWithButton(
                    placeholder = "your password",
                    label = "Password",
                    showPasswordObsecure = true,
                    singleLine = true
                )
            }
            Text(
                text = "or",
                style = MaterialTheme.typography.caption
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonSocial("Login with Google")
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = "Forgot password?",
                    style = MaterialTheme.typography.subtitle2
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