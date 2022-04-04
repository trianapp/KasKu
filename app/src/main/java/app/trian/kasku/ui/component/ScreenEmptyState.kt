package app.trian.kasku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.kasku.R
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 20.09
 * site https://trian.app
 */

@Composable
fun ScreenEmptyState(
    modifier: Modifier=Modifier,
    image:Int,
    title:String,
    subtitle:String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .padding(
                horizontal = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Image data is empty"
        )
       Column {
           Text(
               text = title,
               style = MaterialTheme.typography.h4.copy(
                   fontWeight = FontWeight.Bold,
                   color = MaterialTheme.colors.onBackground
               ),
               textAlign = TextAlign.Center,
               modifier = modifier.fillMaxWidth()
           )
           Spacer(modifier = modifier.height(6.dp))
           Text(
               text = subtitle,
               style = MaterialTheme.typography.caption.copy(
                   fontWeight = FontWeight.Light,
                   color = MaterialTheme.colors.onSurface
               ),
               textAlign = TextAlign.Center,
               modifier = modifier.fillMaxWidth()
           )
       }
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down_empty_state),
            contentDescription = "Below"
        )
        Spacer(modifier = modifier.height(40.dp))
    }
}

@Preview
@Composable
fun PreviewScreenEmptyState() {
    KasKuTheme {
        ScreenEmptyState(
            title = "you have to have atleast 1 month of transaction",
            subtitle = "You can add transaction by tapping the + button below",
            image = R.drawable.bg_empty_1

        )
    }
}