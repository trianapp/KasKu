package app.trian.kasku.ui.pages.budget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.ui.component.AppbarBudget
import app.trian.kasku.ui.component.FormInput
import app.trian.kasku.ui.component.FormInputWithButton
import app.trian.kasku.ui.component.ItemCategory
import app.trian.kasku.ui.theme.KasKuTheme

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
            AppbarBudget(title = "Create Budget") {
                
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
                items(count = 3){
                    ItemCategory(
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
                    placeholder = "Enter budget name"
                )

                FormInputWithButton(
                    label = "Enter budget",
                    placeholder = "Rp 0"
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