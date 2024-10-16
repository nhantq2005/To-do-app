package com.example.todoapp.feature_todo.domain.use_cases

import com.example.todoapp.feature_todo.domain.repository.TaskRepository

class GetTasks(
    private val repository: TaskRepository
) {
}