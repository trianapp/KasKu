package app.trian.kasku.ui.pages.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kasku.R
import app.trian.kasku.ui.component.*
import app.trian.kasku.ui.theme.KasKuTheme

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */
@Composable
fun PageProfile(
    modifier: Modifier = Modifier,
    router: NavHostController
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density


    PageBaseDashboard(
        router = router,
        topAppbar = {
            AppbarProfile()
        },
        content = {
            Column {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        )
                        .background(MaterialTheme.colors.surface)
                        .padding(horizontal = 30.dp)
                ) {
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.bg_profile
                            ),
                            contentDescription = "",
                            modifier = modifier
                                .size(currentWidth / 4)
                                .clip(CircleShape)
                        )
                        Column {
                            Text(
                                text = "Trian Damai",
                                style = MaterialTheme.typography.h4.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colors.primary)
                        .coloredShadow(
                            MaterialTheme.colors.primary
                        )
                        .padding(all = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "United Bank Asia",
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                text = "Rp 1.000.000.000",
                                style = MaterialTheme.typography.body1.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                        }
                        ButtonSmallSecondary(
                            "Update",
                            textColor = MaterialTheme.colors.onPrimary,
                            backgroundColor = MaterialTheme.colors.onPrimary
                        ){

                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                }
                Spacer(modifier = modifier.height(30.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = "triandamai@gmail.com",
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Date of birth",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = "16-09-1998",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewPageProfile() {
    KasKuTheme {
        PageProfile(router = rememberNavController())
    }
}