package com.example.todoapp.feature_todo.presentation.add_edit_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.feature_todo.domain.model.InvalidTaskException
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.domain.use_cases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle:SavedStateHandle
):ViewModel() {

    private val _taskTitle = mutableStateOf(TaskTextFieldState(
        hint = "Task"
    ))
    val taskTitle:State<TaskTextFieldState> = _taskTitle

    private val _taskDetail = mutableStateOf(TaskTextFieldState(
        hint = "Detail"
    ))
    val taskDetail:State<TaskTextFieldState> = _taskDetail

    private val _state = mutableStateOf(AddEditTaskState())
    val state:State<AddEditTaskState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId:Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let {taskId ->
            if(taskId != -1){
                viewModelScope.launch {
                    taskUseCases.getTask(taskId)?.also {task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
//                            isHintVisible = false
                        )
                        _taskDetail.value = taskDetail.value.copy(
                            text = task.detail,
//                            isHintVisible = false
                        )
                        _state.value=state.value.copy(
                            isImportant = task.isImportant
                        )
                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.ChangeDetailFocus -> {
                _taskDetail.value = taskDetail.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskDetail.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredDetail -> {
                _taskDetail.value = taskDetail.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }

            AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                detail = taskDetail.value.text,
                                isImportant = state.value.isImportant,
                                timeStamp = System.currentTimeMillis(),
                                isDone = false,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    }catch (e:InvalidTaskException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Unknow"
                            )
                        )
                    }
                }
            }

            is AddEditTaskEvent.ImportantCheck -> {
                _state.value = state.value.copy(
                    isImportant = !state.value.isImportant
                )
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackBar(val message:String):UiEvent()
        object SaveTask:UiEvent()
    }
}