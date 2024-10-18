package com.example.todoapp.feature_todo.domain.use_cases

import com.example.todoapp.feature_todo.domain.model.InvalidTaskException
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        if(task.title.isBlank()){
            throw InvalidTaskException("The title of task can't be empty")
        }
        repository.insertTask(task)
    }
}