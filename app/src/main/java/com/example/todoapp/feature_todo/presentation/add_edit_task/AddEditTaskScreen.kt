package com.example.todoapp.feature_todo.presentation.add_edit_task

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.todoapp.feature_todo.presentation.Screen
import com.example.todoapp.feature_todo.presentation.add_edit_task.composements.CustomImportantButton
import com.example.todoapp.feature_todo.presentation.add_edit_task.composements.OutlinedHintTextField
import com.example.todoapp.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
){
    val titleState = viewModel.taskTitle.value
    val detailState = viewModel.taskDetail.value
    val importantState = viewModel.state.value

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddEditTaskViewModel.UiEvent.ShowSnackBar ->{
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    AppTheme.appColor.topBarColor
                ),
                title = { 
                    Text(text = Screen.AddEditTaskScreen.title,
                        style = AppTheme.appTypograhy.headline)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    }) {        //back to HomeScreen
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = "Back home",
                            tint = AppTheme.appColor.iconColor)
                    }
                },
                actions = {
                    CustomImportantButton(isCheck = importantState.isImportant,
                        onCheckedChange = {
                            viewModel.onEvent(AddEditTaskEvent.ImportantCheck(importantState.isImportant))
                        }
                    )
                }
            )
        }
    ) { PaddingValues ->
        Box(modifier = Modifier.padding(PaddingValues)
            .fillMaxSize()){
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeTitleFocus(it))
                    },
                    singleLine = true,
                    textStyle = AppTheme.appTypograhy.title
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedHintTextField(
                    text = detailState.text,
                    hint = detailState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskEvent.EnteredDetail(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeDetailFocus(it))
                    },
                    textStyle = AppTheme.appTypograhy.title,
                    modifier = Modifier.fillMaxHeight(0.7f)
                )
            }
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTaskEvent.SaveTask)
                          },
                containerColor = AppTheme.appColor.fabColor,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .padding(25.dp)
            ) {
                Icon(Icons.Default.Check,
                    contentDescription = "Finish",
                    tint = AppTheme.appColor.iconColor)
            }
        }

    }
}