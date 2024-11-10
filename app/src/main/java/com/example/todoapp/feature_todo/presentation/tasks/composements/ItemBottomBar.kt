package com.example.todoapp.feature_todo.presentation.tasks.composements

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.todoapp.feature_todo.presentation.Screen

data class ItemBottomBar(
    val route:String,
    val title:String,
    val icon: ImageVector,
    val isSelected:Boolean
)

