package com.example.todoapp.feature_todo.domain.use_cases

data class TaskUseCases (
    val getTasks: GetTasks,
    val deleteTask: DeleteTask,
    val addTask: AddTask,
    val getTask: GetTask,
    val updateTask: UpdateTask
)