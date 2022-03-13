package app.trian.kasku.ui.pages.budget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.common.numberKeyboardOption
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 03.13
 * site https://trian.app
 */
@Composable
fun PageCreateBudget(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    Scaffold(
        topBar ={
            AppbarBasic(
                title = "Create Budget",
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
            ) {
                
            }
        }
    ) {
        Column {
            Spacer(modifier = modifier.height(30.dp))
            Row(
                modifier = modifier.padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "Choose category",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }
            LazyRow(content = {
                item {
                    ItemAddSelectionBudgetAndCategory()
                }
                items(count = 3){
                    ItemSelectionBudgetAndCategory(
                        name = "Cash"
                    )
                }
            })
            Spacer(modifier = modifier.height(30.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                FormInput(
                    placeholder = "Enter budget name",
                    label = "Budget name"
                )

                FormInputWithButton(
                    label = "Enter budget",
                    placeholder = "Rp 0",
                    singleLine = true,
                    maxLength = 13,
                    keyboardOptions = numberKeyboardOption,
                    masked = CurrencyTransformation()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageCreateBudget() {
    KasKuTheme {
        PageCreateBudget(router = rememberNavController())
    }
}