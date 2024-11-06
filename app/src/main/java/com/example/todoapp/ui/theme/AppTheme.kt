package com.example.todoapp.ui.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ad_coding.noteappcourse.R

@Composable
fun AppTheme(
    content:@Composable ()->Unit
){
    val typograhy = AppTypograhy(
        headline = TextStyle(
            fontFamily = RobotoFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        title = TextStyle(
            fontFamily = RobotoFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        ),
        detail = TextStyle(
            fontFamily = RobotoFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        ),
        subTitle = TextStyle(
            fontFamily = RobotoFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    )

    val color = AppColor(
        topBarColor = Color(0xFFF7FBF2),
        fabColor = Color(0xFFD1E5CE),
        radioButtonColor = Color(0xFF376A3E),
        bottomBarColor = Color(0xFFEBEFE7),
        textColor = Color(0xFF000000),
        iconColor = Color(0xFF424940)
    )

    CompositionLocalProvider(LocalAppTypography provides typograhy,
        LocalAppColor provides color) {
        content.invoke()
    }
}

object AppTheme{
    val appTypograhy:AppTypograhy
    @Composable
    get() = LocalAppTypography.current

    val appColor:AppColor
    @Composable
    get() = LocalAppColor.current
}