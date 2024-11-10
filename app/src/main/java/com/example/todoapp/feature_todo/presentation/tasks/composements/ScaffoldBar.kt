package com.example.todoapp.feature_todo.presentation.tasks.composements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.feature_todo.presentation.Screen
import com.example.todoapp.feature_todo.presentation.tasks.TaskViewModel
import com.example.todoapp.feature_todo.presentation.tasks.TasksEvent
import com.example.todoapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldBar(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {

    val itemsBar = listOf(
        ItemBottomBar(
            route = Screen.HomeScreen.route,
            title = "To do",
            icon = Icons.Default.List,
            isSelected = true
        ),
        ItemBottomBar(
            route = Screen.ImportantTaskScreen.route,
            title = "Important",
            icon = Icons.Default.BookmarkBorder,
            isSelected = false
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    AppTheme.appColor.topBarColor
                ),
                title = {
                    Text(
                        text = "To do",
                        style = AppTheme.appTypograhy.headline
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
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTaskScreen.route)
                },
                containerColor = AppTheme.appColor.fabColor
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add task button",
                    tint = AppTheme.appColor.iconColor
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = AppTheme.appColor.bottomBarColor,
                contentColor = AppTheme.appColor.iconColor,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    itemsBar.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    style = AppTheme.appTypograhy.subTitle
                                )
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = "To do Screen Button",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = AppTheme.appColor.selectedItem
                            )

                        )
                    }

                }

            }
        }
    ) { PaddingValues ->
        Box(
            modifier = Modifier
                .padding(PaddingValues)
                .padding(10.dp)
        ) {
            content()
        }
        when(selectedItemIndex){
            0->navController.navigate(Screen.HomeScreen.route)
            1->navController.navigate(Screen.ImportantTaskScreen.route)
        }
    }
}

fun navScreen(selectedIndex:Int){

}

