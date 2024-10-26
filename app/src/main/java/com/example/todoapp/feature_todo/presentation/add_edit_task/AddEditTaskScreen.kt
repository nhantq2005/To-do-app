package com.example.todoapp.feature_todo.presentation.add_edit_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.feature_todo.presentation.add_edit_task.composements.CustomImportantButton
import com.example.todoapp.feature_todo.presentation.add_edit_task.composements.OutlinedHintTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
//    navController: NavController,
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
//                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = "Add")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {        //back to HomeScreen
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back home")
                    }
                },
                actions = {
                    CustomImportantButton(isCheck = importantState.isImportant,
                        onCheckedChange = { viewModel.onEvent(AddEditTaskEvent.ImportantCheck(importantState.isImportant)) })
                }
            )
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditTaskEvent.SaveTask) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Check, contentDescription = "Finish")
            }
        }
    ) { PaddingValues ->
        Box(modifier = Modifier.padding(PaddingValues)){
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
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge
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
                    isHintVisible = detailState.isHintVisible,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }

    }
}