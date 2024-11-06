package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.feature_todo.domain.util.OrderType
import com.example.todoapp.feature_todo.domain.util.TaskOrder
import com.example.todoapp.ui.theme.AppTheme

@Composable
fun OrderSection(
    modifier: Modifier =Modifier,
    taskOrder: TaskOrder = TaskOrder.Date(OrderType.Ascending),
    onOrderChange:(TaskOrder)->Unit
){
//    Card(modifier = Modifier.fillMaxWidth()
//        .zIndex(2f),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        )
//    ) {
//        Row(
//            modifier = modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Text(
//                text = "Sort by:",
//                style = MaterialTheme.typography.headlineMedium
//            )
//
//            DefaultRadioButton(
//                text = "Date",
//                selected = taskOrder is TaskOrder.Date,
//                onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType))})
//
//            DefaultRadioButton(
//                text = "Title",
//                selected = taskOrder is TaskOrder.Title,
//                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType))})
//        }
//    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
            Text(
                text = "Sort by:",
                style = AppTheme.appTypograhy.headline.copy(fontSize = 20.sp)
            )

            CustomRadioButton(
                text = "Date",
                selected = taskOrder is TaskOrder.Date,
                onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType))})

            CustomRadioButton(
                text = "Title",
                selected = taskOrder is TaskOrder.Title,
                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType))}
            )
    }
}