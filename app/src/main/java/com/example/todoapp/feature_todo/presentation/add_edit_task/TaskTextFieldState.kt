package com.example.todoapp.feature_todo.presentation.add_edit_task

data class TaskTextFieldState(
    val text:String ="",
    val hint:String = "",
    val isHintVisible:Boolean = true
)
