package com.muffar.remindtask.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muffar.remindtask.screen.main.component.MainBottomBar
import com.muffar.remindtask.ui.navigation.Screens
import com.muffar.remindtask.ui.navigation.tasksScreen
import com.muffar.remindtask.ui.navigation.toAddTask

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val localNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainBottomBar(
                navController = localNavController,
                onAddClick = { navController.toAddTask() }
            )
        }
    ) {
        Box(modifier = modifier.padding(it)) {
            NavHost(navController = localNavController, startDestination = Screens.Tasks.route) {
                tasksScreen(
                    toAddTask = { task -> navController.toAddTask(task) }
                )
                composable(Screens.Notes.route) { Text(text = "Notes") }
            }
        }
    }
}