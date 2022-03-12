package app.trian.kasku.ui.component.calendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import app.trian.kasku.ui.component.calendar.util.MonthHeaderPosition
import com.google.accompanist.pager.ExperimentalPagerApi
import compose.icons.Octicons
import compose.icons.octicons.X24
import java.time.LocalDate

/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 23.46
 * site https://trian.app
 */
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun DialogCalendarPickerFullScreen(
    show:Boolean=false,
    modifier: Modifier=Modifier,
    onDismiss:()->Unit={},
    onDateSelected:(LocalDate)->Unit={}
) {
    val ctx = LocalContext.current
    val currentHeight= ctx
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density



    if(show){
        Dialog(
            onDismissRequest =onDismiss,
            properties = DialogProperties(
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true,
                dismissOnBackPress = true,
                securePolicy = SecureFlagPolicy.SecureOn
            )
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

            ) {
                Column(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 05f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() } // This is mandatory
                        ) {
                            onDismiss()
                        }
                ) {

                }

                    Column(
                        modifier = modifier
                            .align(Alignment.TopCenter)
                            .height((currentHeight / 2) + (currentHeight / 7))
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp
                                )
                            )
                            .background(color = MaterialTheme.colors.surface)
                            .padding(
                                all = 20.dp,
                            )
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Select date transaction",
                                style = MaterialTheme.typography.h4.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            IconToggleButton(checked = false, onCheckedChange = {
                                onDismiss()
                            }) {
                                Icon(imageVector = Octicons.X24, contentDescription = "")
                            }
                        }
                        Spacer(modifier = modifier.height(16.dp))
                        SelectableCalendar(
                            monthHeaderPosition = MonthHeaderPosition.BOTTOM
                        )
                    }


            }
        }
    }
}