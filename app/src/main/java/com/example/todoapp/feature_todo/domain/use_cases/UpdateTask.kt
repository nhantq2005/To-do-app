package com.example.todoapp.feature_todo.domain.use_cases

import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope

class UpdateTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        return repository.updateTask(task.copy(isDone = !task.isDone))
    }
}