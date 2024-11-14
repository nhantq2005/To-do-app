package com.example.todoapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColor(
    val topBarColor:Color = Color.Unspecified,
    val radioButtonColor:Color = Color.Unspecified,
    val fabColor:Color = Color.Unspecified,
    val bottomBarColor:Color = Color.Unspecified,
    val textColor:Color = Color.Unspecified,
    val iconColor:Color = Color.Unspecified,
    val selectedItem:Color = Color.Unspecified,
    val actionLabel:Color = Color.Unspecified
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor()
}