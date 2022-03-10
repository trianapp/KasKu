package app.trian.kasku.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 13.45
 * site https://trian.app
 */
@Composable
fun DottedLine(
    color:Color=MaterialTheme.colors.primary
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
    Canvas(
        modifier = Modifier.fillMaxWidth().height(1.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }

}