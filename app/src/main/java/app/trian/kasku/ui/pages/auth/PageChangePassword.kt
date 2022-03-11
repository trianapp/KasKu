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
    Scaffold(
        topBar = {
           AppbarBasic(
               title = "Change password"
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
                    placeholder = "your new password",
                    label = "New password",
                    showPasswordObsecure = true,
                    singleLine = true
                )
                FormInputWithButton(
                    placeholder = "confirm new password",
                    label = "Confirm password",
                    showPasswordObsecure = true,
                    singleLine = true
                )
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