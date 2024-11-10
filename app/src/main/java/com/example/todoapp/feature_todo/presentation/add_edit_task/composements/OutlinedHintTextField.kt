package com.example.todoapp.feature_todo.presentation.add_edit_task.composements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedHintTextField(
    text:String,
    hint:String,
    modifier: Modifier = Modifier,
    onValueChange:(String)->Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine:Boolean=false,
    onFocusChange:(FocusState)->Unit
){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                onFocusChange(it)
            }
            .padding(horizontal = 10.dp),
        label = { Text(text = hint,
            style = AppTheme.appTypograhy.title.copy(fontSize = 15.sp)) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = AppTheme.appColor.iconColor,
            focusedLabelColor = AppTheme.appColor.iconColor,
            cursorColor = AppTheme.appColor.iconColor

        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}