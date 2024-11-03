package com.example.todoapp.feature_todo.domain.use_cases

import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.repository.TaskRepository
import com.example.todoapp.feature_todo.domain.util.OrderType
import com.example.todoapp.feature_todo.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Date(OrderType.Ascending)
    ):Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when (taskOrder) {
                        is TaskOrder.Date -> {
                            tasks.sortedBy { it.timeStamp }
                        }

                        is TaskOrder.Title -> {
                            tasks.sortedBy { it.title.lowercase() }
                        }
                    }
                }

            }
        }
    }
}