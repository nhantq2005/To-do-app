package com.example.todoapp.feature_todo.presentation.tasks

import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.util.TaskOrder

sealed class TasksEvent {
    data class Order(val taskOrder: TaskOrder):TasksEvent()

    data class DeleteTask(val task: Task):TasksEvent()

    object RestoreTask:TasksEvent()

    object ToggleOrderSection:TasksEvent()

    data class CompletedTask(val task: Task):TasksEvent()
}