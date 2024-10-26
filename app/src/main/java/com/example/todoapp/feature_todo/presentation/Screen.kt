package com.example.todoapp.feature_todo.presentation

sealed class Screen(val route:String) {
    object HomeScreen:Screen("home_screen")
    object AddEditTaskScreen:Screen("add_edit_task_screen")
    object SplashScreen:Screen("splash_screen")
    object ImportantTaskScreen:Screen("important_task_screen")
}