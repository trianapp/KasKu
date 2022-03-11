package app.trian.kasku.ui.pages.bank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.common.numberKeyboardOption
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.HexToJetpackColor
import app.trian.kasku.ui.theme.KasKuTheme
import app.trian.kasku.ui.theme.listGradient
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

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


    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Add account",
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
            ){
                router.popBackStack()
            }
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
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(fraction = 0.4f)
                )
                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = "Add primary bank account",
                    style = MaterialTheme.typography.body1
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {


                FormInputPickColor(
                    label = "Select color",
                    onSelect = {}
                )
                FormInput(
                    initialValue = "",
                    placeholder = "Account name here",
                    label = "Account name",
                    singleLine = true,
                    onChange = {

                    }
                )
                FormInputWithButton(
                    initialValue = "",
                    placeholder = "0",
                    label = "Starter amount",
                    singleLine = true,
                    masked = CurrencyTransformation("#.###.###.###.###"),
                    keyboardOptions = numberKeyboardOption,
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

                    },
                    onSubmit = {
                        router.navigate(Routes.ADD_BANK_SUCCESS)
                    }
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