package com.example.todoapp.feature_todo.presentation.tasks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.feature_todo.domain.model.Task
import com.example.todoapp.feature_todo.presentation.tasks.composements.DropDownMenu
import com.example.todoapp.feature_todo.presentation.tasks.composements.OrderSection
import com.example.todoapp.feature_todo.presentation.tasks.composements.TaskItem
import com.example.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val doneState = viewModel.isDoneTask.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
//        scaffoldState = scaffoldState,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(text = "To do",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                        },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(TasksEvent.ToggleOrderSection)
                    }) {
                        Icon(
                            Icons.Default.Sort,
                            contentDescription = "Sort Button"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Add task button")
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Default.List,
                            contentDescription = "To do Screen Button",
                            modifier = Modifier.size(30.dp))
                    }
                    IconButton(
                        onClick = { /*TODO*/ }
                        ) {
                        Icon(Icons.Default.BookmarkBorder,
                            contentDescription = "Impotant Screen Button",
                            modifier = Modifier.size(30.dp))
                    }
                }
            }
        }
    ) { PaddingValues ->
        Box(modifier = Modifier
            .padding(PaddingValues)
            .padding(10.dp)) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    onOrderChange = {viewModel.onEvent(TasksEvent.Order(it))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    taskOrder = state.taskOrder
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.tasks){atask ->
                    TaskItem(
                        task = atask,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        onDeleteClick = {
                            //Delete Task
                            viewModel.onEvent(TasksEvent.DeleteTask(atask))
                            scope.launch {
                                //Show snack bar to restore task
                                val result = snackbarHostState.showSnackbar(
                                    message = "Task deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(TasksEvent.RestoreTask)
                                }
                            }
                        },
                        isDone = atask.isDone,
                        onDoneClick = {
                            viewModel.onEvent(TasksEvent.CheckTask(doneState.isDoneTask))
//                            viewModel.onEvent(TasksEvent.DeleteTask(task))
                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ToDoAppTheme {
//        HomeScreen()
//    }
//}