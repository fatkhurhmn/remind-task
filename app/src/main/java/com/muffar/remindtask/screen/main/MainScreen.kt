package com.muffar.remindtask.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.muffar.remindtask.screen.main.component.MainBottomBar
import com.muffar.remindtask.ui.navigation.Screens
import com.muffar.remindtask.ui.navigation.notesScreen
import com.muffar.remindtask.ui.navigation.tasksScreen
import com.muffar.remindtask.ui.navigation.toAddNote
import com.muffar.remindtask.ui.navigation.toAddTask

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val localNavController = rememberNavController()
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            MainBottomBar(
                navController = localNavController,
                onAddClick = {
                    if (currentRoute == Screens.Tasks.route) {
                        navController.toAddTask()
                    } else {
                        navController.toAddNote()
                    }
                }
            )
        }
    ) {
        Box(modifier = modifier.padding(it)) {
            NavHost(navController = localNavController, startDestination = Screens.Tasks.route) {
                tasksScreen(
                    toAddTask = { task -> navController.toAddTask(task) }
                )
                notesScreen()
            }
        }
    }
}