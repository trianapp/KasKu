package app.trian.kasku.ui.pages.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.common.toastError
import app.trian.kasku.domain.CategoryIconModel
import app.trian.kasku.domain.listIconCategory
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PageAddCategory(
    modifier: Modifier = Modifier,
    router: NavHostController,
    onSubmit:(
        categoryName:String,
        icon:Int
    )->Unit={_,_->}
) {
    val ctx = LocalContext.current
    var selectedIcons by remember {
        mutableStateOf<CategoryIconModel?>(null)
    }
    var categoryName by remember {
        mutableStateOf("")
    }

    fun submit(){
        if(selectedIcons == null){
            ctx.toastError("Please select icon for category!")
            return
        }
        if(categoryName.isBlank()){
            ctx.toastError("Please input category name!")
            return
        }

        onSubmit(
            categoryName,
            selectedIcons!!.code
        )
    }
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Add category",
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
            )
        },
        backgroundColor = BackgroundDashboard,
        bottomBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 30.dp,
                        vertical = 4.dp
                    )
            ) {
                ButtonPrimary("Save"){
                    submit()
                }
            }
        }
    ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                vertical = 30.dp,
                horizontal = 30.dp
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                FormInput(
                    label = "Category name",
                    placeholder = "Category name here",
                    initialValue = categoryName,
                    onChange = {
                        categoryName = it
                    }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = "Select Icon",
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                LazyColumn(
                    content = {
                        gridItems(
                            listIconCategory,
                            columnCount = 4
                        ){
                            icon->
                            ItemIconCategory(
                                name = icon.iconName,
                                icon = icon.icon,
                                selected = selectedIcons?.iconName == icon.iconName
                            ){
                                selectedIcons = icon
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageAddCategory() {
    KasKuTheme {
        PageAddCategory(router = rememberNavController())
    }
}