package com.example.todoapp.feature_todo.presentation.add_edit_task.composements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedHintTextField(
    text:String,
    hint:String,
    modifier: Modifier = Modifier,
    isHintVisible:Boolean = true,
    onValueChange:(String)->Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine:Boolean=false,
    onFocusChange:(FocusState)->Unit
){
    Box(modifier = modifier){
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if(isHintVisible){
            Text(
                text = hint,
                style = textStyle,
                color = Color.DarkGray
            )
        }


    }
}