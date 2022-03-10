package app.trian.kasku.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.R
import app.trian.kasku.ui.theme.CircleIconCategory
import app.trian.kasku.ui.theme.KasKuTheme

@Composable
fun ItemIconCategory(
    modifier:Modifier=Modifier,
    name:String="",
    icon:Int= R.drawable.ic_category_hotel,
    selected:Boolean=false,
    onClick:()->Unit={}
) {

    Box(
        modifier = modifier
            .padding(all = 6.dp)
    ) {
        Box(
            modifier = modifier
                .defaultMinSize(
                    minWidth = 30.dp,
                    minHeight = 30.dp
                )
                .border(
                    border = BorderStroke(
                        width = if (selected) 2.dp else 0.dp,
                        color = if (selected) MaterialTheme.colors.primary else Color.Transparent
                    ),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(CircleIconCategory)
                .padding(all = 6.dp)
                .clickable {
                    onClick()
                }

        ){
            Image(
                painter = painterResource(id = icon) ,
                contentDescription = name,
                modifier = modifier
                    .align(Alignment.Center),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemIconCategory() {
    KasKuTheme {
        ItemIconCategory(
            selected = true
        )
    }
}