package app.trian.kasku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 20/03/22 - 13.28
 * site https://trian.app
 */

@Composable
fun DialogOptionPickImage(
    show:Boolean,
    onCamera:()->Unit,
    onGallery:()->Unit,
    onDismiss:()->Unit
) {
    if(show) {
        Dialog(onDismissRequest = onDismiss) {
            ScreenDialogOptionPickImage(
                onCamera = onCamera,
                onGallery = onGallery
            )
        }

    }
}

@Composable
fun ScreenDialogOptionPickImage(
    modifier: Modifier=Modifier,
    onCamera: () -> Unit={},
    onGallery:()->Unit={}
) {

    Box (
        modifier = modifier.padding(
            all = 20.dp
        )
            ){
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.background)
                .padding(all = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Select Image Source")
            Row {
                ItemSelectionBudgetAndCategory(
                    name = "Camera"
                ){
                    onCamera()
                }
                ItemSelectionBudgetAndCategory(
                    name = "Gallery"
                ){
                    onGallery()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialogOptionPickImage() {
    KasKuTheme {
        ScreenDialogOptionPickImage()
    }
}