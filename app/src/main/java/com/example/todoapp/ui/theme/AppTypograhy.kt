package com.example.todoapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class AppTypograhy(
    val headline:TextStyle = TextStyle.Default,
    val title:TextStyle = TextStyle.Default,
    val detail:TextStyle = TextStyle.Default,
    val subTitle:TextStyle = TextStyle.Default
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypograhy()
}