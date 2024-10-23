package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun CustomCheckButton(
    isCheck:Boolean = false,
    onCheckedChange:()->Unit
){
    var isDone by rememberSaveable {
        mutableStateOf(false)
    }
    IconButton(onClick = {
        isDone = !isDone
    }) {
        if(isDone){
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = "Done"
            )
        }
        else{
            Icon(
                Icons.Outlined.Circle,
                contentDescription = "Not Done"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CheckButotngPreview() {
//    ToDoAppTheme {
//        CustomCheckButton()
//    }
//}