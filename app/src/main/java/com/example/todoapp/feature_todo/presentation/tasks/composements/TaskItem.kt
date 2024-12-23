package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.ui.theme.AppTheme
import com.example.todoapp.ui.theme.ToDoAppTheme

//import com.example.todoapp.ui.theme.ToDoAppTheme

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    isDone: Boolean,
    onDoneClick: () -> Unit
) {

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .shadow(15.dp, RoundedCornerShape(10.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomCheckButton(
                isDone = isDone,
                onCheckedChange = onDoneClick
            )
            Text(
                text = task.title,
                style = AppTheme.appTypograhy.title,
                color = AppTheme.appColor.textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None

            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete Task Button"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ToDoAppTheme {
//
//    }
//}