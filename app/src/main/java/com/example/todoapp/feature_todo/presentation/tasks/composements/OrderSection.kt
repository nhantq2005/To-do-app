package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_todo.domain.util.OrderType
import com.example.todoapp.feature_todo.domain.util.TaskOrder

@Composable
fun OrderSection(
    modifier: Modifier =Modifier,
    taskOrder: TaskOrder = TaskOrder.Date(OrderType.Ascending),
    onOrderChange:(TaskOrder)->Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Sort by:",
            style = MaterialTheme.typography.headlineMedium
        )

        DefaultRadioButton(
            text = "Date",
            selected = taskOrder is TaskOrder.Date,
            onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType))})

        DefaultRadioButton(
            text = "Title",
            selected = taskOrder is TaskOrder.Title,
            onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType))})
    }
}