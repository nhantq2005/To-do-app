package com.example.todoapp.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.feature_todo.presentation.add_edit_task.AddEditTaskScreen
import com.example.todoapp.feature_todo.presentation.tasks.ImportantTaskScreen
import com.example.todoapp.feature_todo.presentation.tasks.HomeScreen
import com.example.todoapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                    ){
                    composable(Screen.HomeScreen.route){
                        HomeScreen(navController = navController)
                    }

                    composable(Screen.ImportantTaskScreen.route){
                        ImportantTaskScreen(navController = navController)
                    }

                    composable(Screen.AddEditTaskScreen.route +
                    "?taskId={taskId}",
                        arguments = listOf(
                            navArgument(
                                name = "taskId"
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                            AddEditTaskScreen(navController = navController)
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ToDoAppTheme {
//    }
//}