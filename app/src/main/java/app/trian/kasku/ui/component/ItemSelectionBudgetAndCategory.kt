package app.trian.kasku.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.R
import app.trian.kasku.common.coloredShadow
import app.trian.kasku.common.dashedBorder
import app.trian.kasku.ui.theme.CircleIconCategory
import app.trian.kasku.ui.theme.DisableContentColor
import app.trian.kasku.ui.theme.KasKuTheme
import compose.icons.Octicons
import compose.icons.octicons.Plus24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 00.34
 * site https://trian.app
 */
@Composable
fun ItemSelectionBudgetAndCategory(
    modifier: Modifier=Modifier,
    icon:Int= R.drawable.ic_category_bank,
    name:String="",
    selected:Boolean = false,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = currentWidth / 2 - (currentWidth/10)

   Row(
       modifier = modifier
           .padding(
               vertical = 16.dp,
               horizontal = 10.dp
           )
           .background(Color.Transparent)
   ) {

       Column(
           modifier = modifier
               .width(cardWidth)
               .height((currentWidth / 2))
               .clip(MaterialTheme.shapes.large)
               .coloredShadow(
                   color = DisableContentColor
               )
               .border(
                   width = if (selected) 1.dp else 0.dp,
                   color = if (selected) MaterialTheme.colors.primary else Color.Transparent,
                   shape = MaterialTheme.shapes.large
               )
               .background(MaterialTheme.colors.surface)
               .clickable(onClick = { onClick() })
               .padding(
                   all = 16.dp
               ),
           horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.SpaceBetween
       ) {
           Box(
               modifier = modifier
                   .size(cardWidth / 2)
                   .padding(all = 6.dp)
                   .clip(CircleShape)
                   .background(CircleIconCategory)

           ){
               Image(
                   painter = painterResource(id = icon) ,
                   contentDescription = "",
                   modifier = modifier
                       .align(Alignment.Center)
                       .size(cardWidth / 2 - 10.dp)
                       .padding(all = 16.dp),
                   contentScale = ContentScale.FillWidth
               )
           }
           Text(
               text = name,
               style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                   color = if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
               )
           )
       }

   }
}

@Composable
fun ItemAddSelectionBudgetAndCategory(
    modifier: Modifier=Modifier,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = currentWidth / 2 - (currentWidth/10)

    Row(
        modifier = modifier
            .padding(
                vertical = 16.dp,
                horizontal = 10.dp
            )
            .background(Color.Transparent)
    ) {

        Column(
            modifier = modifier
                .width(cardWidth)
                .height((currentWidth / 2))
                .clip(MaterialTheme.shapes.large)
                .coloredShadow(
                    color = DisableContentColor
                )
                .dashedBorder(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colors.primary
                    ),
                    shape = RoundedCornerShape(20.dp),
                    on = 3.dp,
                    off = 3.dp

                )
                .background(MaterialTheme.colors.surface)
                .clickable(onClick = { onClick() })
                .padding(
                    all = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .size(cardWidth / 2)
                    .padding(all = 6.dp)

            ){
                Icon(
                    imageVector = Octicons.Plus24 ,
                    contentDescription = "",
                    modifier = modifier
                        .align(Alignment.Center)
                        .size(cardWidth / 2 - 10.dp)
                        .padding(all = 16.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
            Text(
                text = "Add",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color =  MaterialTheme.colors.primary
                )
            )
        }

    }
}
@Preview
@Composable
fun PreviewItemCategory(){
    KasKuTheme {
        val itemsName = listOf(
            "Bank",
            "Cash",
            "Food",
            "Transportation"
        )
        val itemsIcon = listOf(
            R.drawable.ic_category_van,
            R.drawable.ic_category_van,
            R.drawable.ic_category_van,
            R.drawable.ic_category_van
        )
        Column {
            LazyRow(
                modifier = Modifier.background(MaterialTheme.colors.background),
                content = {
                    item{
                        ItemAddSelectionBudgetAndCategory()
                    }
                    items(count = 4){
                            index ->
                        ItemSelectionBudgetAndCategory(
                            name=itemsName[index],
                            icon=itemsIcon[index],
                            selected = index ==1
                        )
                    }
                })

        }

    }
}
