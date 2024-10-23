package com.example.todoapp.feature_todo.presentation.add_edit_task.composements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun CustomImprtantButton(
    isCheck:Boolean=false,
    onCheckedChange:()->Unit,
    modifier:Modifier=Modifier
){
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }
    IconButton(onClick = {
        isChecked = !isChecked
    },
        modifier = modifier
        ) {
        if(isChecked){
            Icon(
                Icons.Default.Bookmark,
                tint = Color(239, 192, 0),
                contentDescription = "Important"
            )
        }else{
            Icon(
                Icons.Default.BookmarkBorder,
                contentDescription = "Not Important"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CustomCheckButtonPreview() {
//    ToDoAppTheme {
//        CustomImprtantButton()
//    }
//}