package com.example.todoapp.feature_todo.presentation

sealed class Screen(val route:String, val title:String) {
    object HomeScreen:Screen("home_screen","To do")
    object AddEditTaskScreen:Screen("add_edit_task_screen","Add")
    object ImportantTaskScreen:Screen("important_task_screen","Important")
}