package com.example.todoapp.feature_todo.presentation.tasks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.presentation.Screen
import com.example.todoapp.feature_todo.presentation.tasks.composements.OrderSection
import com.example.todoapp.feature_todo.presentation.tasks.composements.ScaffoldBar
import com.example.todoapp.feature_todo.presentation.tasks.composements.TaskItem
import com.example.todoapp.ui.theme.AppTheme
import com.example.todoapp.ui.theme.ToDoAppTheme

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ScaffoldBar(
        title = Screen.HomeScreen.title,
        navController = navController,
        snackbarHostState=snackbarHostState,
        viewModel = viewModel
    ) {
        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier.zIndex(1f)
        ) {
            OrderSection(
                onOrderChange = {
                    viewModel.onEvent(TasksEvent.Order(it))
                    viewModel.onEvent(TasksEvent.ToggleOrderSection)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                taskOrder = state.taskOrder
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.tasks.sortedBy { task -> task.isDone }){atask ->
                TaskItem(
                    task = atask,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.AddEditTaskScreen.route
                                    +"?taskId=${atask.id}")
                        },
                    onDeleteClick = {
                        //Delete Task
                        viewModel.onEvent(TasksEvent.DeleteTask(atask))
                        scope.launch {
                            //Show snack bar to restore task
                            val result = snackbarHostState.showSnackbar(
                                message = "Task deleted",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Short
                            )
                            if(result == SnackbarResult.ActionPerformed){
                                viewModel.onEvent(TasksEvent.RestoreTask)
                            }
                        }
                    },
                    isDone = atask.isDone,
                    onDoneClick = {
                        viewModel.onEvent(TasksEvent.CompletedTask(atask))
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppTheme {
//        HomeScreen()
    }
}
