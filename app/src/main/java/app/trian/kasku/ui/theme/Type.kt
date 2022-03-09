package app.trian.kasku.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.trian.kasku.R

val fontFamily = FontFamily(
    Font(R.font.gt_walsheim_extra_bold, FontWeight.ExtraBold),
    Font(R.font.gt_walsheim_extra_bold_oblique,FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.gt_walsheim_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.gt_walsheim_bold_oblique, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.gt_walsheim_medium, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.gt_walsheim_medium_oblique, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.gt_walsheim_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.gt_walsheim_medium_oblique, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.gt_walsheim_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.gt_walsheim_regular_oblique, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.gt_walsheim_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.gt_walsheim_light_oblique, FontWeight.Light, FontStyle.Italic),
    Font(R.font.gt_walsheim_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.gt_walsheim_thin_oblique, FontWeight.Thin, FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily =  fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.2.sp
    ),
    h5 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),
    subtitle1 = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily= fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.5.sp
    )

)
