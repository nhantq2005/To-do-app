package com.example.todoapp.feature_todo.presentation.tasks

import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.util.OrderType
import com.example.todoapp.feature_todo.domain.util.TaskOrder

data class TaskState(
    val tasks:List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Date(OrderType.Ascending),
    val isOrderSectionVisible:Boolean = false
)