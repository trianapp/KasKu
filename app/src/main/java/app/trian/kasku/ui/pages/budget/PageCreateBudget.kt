package app.trian.kasku.ui.pages.budget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.common.CurrencyTransformation
import app.trian.kasku.common.toastError
import app.trian.kasku.data.local.entity.Category
import app.trian.kasku.ui.Routes
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
    categories:List<Category> = listOf(),
    router: NavHostController,
    onSubmit:(
        categoryId:String,
        budgetName:String,
        budgetDescription:String,
        budgetAmount:Int
    )->Unit={_,_,_,_->}
) {
    val ctx = LocalContext.current

    var selectedCategory by remember {
        mutableStateOf<Category?>(null)
    }

    var budgetName by remember {
        mutableStateOf("")
    }

    var budgetAmount by remember {
        mutableStateOf("")
    }

    var budgetDescription by remember {
        mutableStateOf("")
    }
    fun submit(){
        if(selectedCategory == null){
            ctx.toastError("Please select 1 category")
            return
        }
        if(budgetName.isBlank()){
            ctx.toastError("Budget name cannot empty!")
            return
        }
        if(budgetAmount.isBlank()){
            ctx.toastError("Amount must greater than 0!")
            return
        }
        onSubmit(
            selectedCategory!!.uid,
            budgetName,
            budgetDescription,
            budgetAmount.toInt()
        )
    }
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
                    ItemAddSelectionBudgetAndCategory(){
                        router.navigate(Routes.ADD_CATEGORY)
                    }
                }
                items(categories){
                    category->
                    ItemSelectionBudgetAndCategory(
                        name = category.name,
                        icon = category.icon,
                        selected = category.uid == selectedCategory?.uid,
                        onClick = {
                            selectedCategory = category
                        }
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
                    label = "Budget name",
                    initialValue = budgetName,
                    onChange = {
                        budgetName = it
                    }
                )
                FormInput(
                    placeholder = "What purpose this budget?",
                    label = "Budget description",
                    initialValue = budgetDescription,
                    onChange = {
                        budgetDescription = it
                    }
                )

                FormInputWithButton(
                    label = "Enter budget",
                    placeholder = "Rp 0",
                    singleLine = true,
                    maxLength = 13,
                    keyboardType = KeyboardType.Number,
                    masked = CurrencyTransformation(),
                    initialValue = budgetAmount,
                    onChange = {
                        budgetAmount = it
                    },
                    onSubmit = ::submit
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