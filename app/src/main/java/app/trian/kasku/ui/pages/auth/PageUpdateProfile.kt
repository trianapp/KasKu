package app.trian.kasku.ui.pages.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.fontFamily
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 16/03/22 - 01.34
 * site https://trian.app
 */

@Composable
fun PageUpdateProfile(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Edit Profile",
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {
                        router.popBackStack()
                    }) {
                        Icon(imageVector = Octicons.ArrowLeft24, contentDescription = "")
                    }
                }
            )
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
                    text = "Update your profile",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "You can update your profile",
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                FormInputWithButton(
                    placeholder = "Date of Birth",
                    label = "Date of birth",
                    singleLine = true,
                    initialValue = password,
                    onChange = {
                        password = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {

                        }
                    )
                ){

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageUpdateProfile() {
    KasKuTheme {
        PageUpdateProfile(router = rememberNavController())
    }
}