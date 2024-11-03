package com.example.todoapp.feature_todo.data.repository

import com.example.todoapp.feature_todo.data.data_source.TaskDao
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao:TaskDao
):TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTask()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        return dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return dao.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        return dao.updateTask(task)
    }
}