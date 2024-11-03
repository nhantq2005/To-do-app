package com.example.todoapp.feature_todo.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.use_cases.TaskUseCases
import com.example.todoapp.feature_todo.domain.util.OrderType
import com.example.todoapp.feature_todo.domain.util.TaskOrder
import com.example.todoapp.feature_todo.presentation.add_edit_task.AddEditTaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
):ViewModel() {

    private val _state = mutableStateOf(TaskState())
    val state:State<TaskState> = _state

    private var recentTask:Task? = null

    private var getTasksJob: Job? = null

    init {
        getTasks(TaskOrder.Date(OrderType.Ascending))           //default sort
    }
    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                    recentTask = event.task
                }
            }
            is TasksEvent.Order -> {
                if(state.value.taskOrder::class == event.taskOrder::class &&
                    state.value.taskOrder.orderType == event.taskOrder.orderType){     //if not have ::class -> check reference
                    return
                }
                getTasks(event.taskOrder)
            }
            TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(recentTask ?: return@launch)  //if null return the launch
                    recentTask = null

                }
            }
            TasksEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is TasksEvent.CompletedTask -> {
                viewModelScope.launch {
                    taskUseCases.updateTask(event.task)
                }
            }
        }
    }
    private fun getTasks(taskOrder: TaskOrder){
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks(taskOrder)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,          //update
                    taskOrder = taskOrder
                )
            }.launchIn(viewModelScope)
    }
}