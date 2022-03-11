package app.trian.kasku.ui.pages.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.domain.CategoryIconModel
import app.trian.kasku.domain.listIconCategory
import app.trian.kasku.ui.Routes
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.BackgroundDashboard
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Plus24

/**
 *
 * author Trian Damai
 * created_at 11/03/22 - 23.38
 * site https://trian.app
 */
@Composable
fun PageListCategory(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    var selectedIcons by remember {
        mutableStateOf<CategoryIconModel?>(null)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {

                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                title = {
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    ),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface,
                actions = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            router.navigate(Routes.ADD_CATEGORY)
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.Plus24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
        backgroundColor = BackgroundDashboard,
    ) {
        Column(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            LazyColumn(content = {
                items(count = 10){
                    ItemListCategory()
                }
            })
        }
    }
}

@Preview
@Composable
fun PreviewListCategory() {
    KasKuTheme {
        PageListCategory(router = rememberNavController())
    }
}