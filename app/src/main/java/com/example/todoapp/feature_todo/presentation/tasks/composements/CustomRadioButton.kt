package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_todo.presentation.tasks.HomeScreen
import com.example.todoapp.ui.theme.AppTheme
import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun CustomRadioButton(
    text:String,
    selected:Boolean,
    onSelect:()->Unit,
    modifier: Modifier = Modifier
){
     Row(
         modifier = modifier,
         verticalAlignment = Alignment.CenterVertically
     ){
         RadioButton( selected = selected,
             onClick = onSelect,
             colors = RadioButtonDefaults.colors(
                 selectedColor = AppTheme.appColor.radioButtonColor
             )
         )
         Text(
             text = text,
             style = AppTheme.appTypograhy.title
         )
     }
}

@Preview(showBackground = true)
@Composable
fun RadioCustomPreview() {
    AppTheme {
        var check by remember {
            mutableStateOf(false)
        }
        CustomRadioButton(text = "Title", selected = check, onSelect = { check = !check })
    }
}