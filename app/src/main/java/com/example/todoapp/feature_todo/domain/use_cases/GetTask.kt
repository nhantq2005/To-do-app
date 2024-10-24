package com.example.todoapp.feature_todo.domain.use_cases

import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.repository.TaskRepository

class GetTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id:Int): Task?{
        return repository.getTaskById(id)
    }
}