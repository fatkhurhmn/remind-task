package com.muffar.remindtask.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muffar.remindtask.screen.main.component.MainBottomBar
import com.muffar.remindtask.screen.tasks.list.TasksScreen
import com.muffar.remindtask.ui.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val localNavController = rememberNavController()
    Scaffold(
        bottomBar = { MainBottomBar(navController = localNavController) }
    ) {
        Box(modifier = modifier.padding(it)) {
            NavHost(navController = localNavController, startDestination = Screens.Tasks.route) {
                composable(Screens.Tasks.route) { TasksScreen() }
                composable(Screens.Notes.route) { Text(text = "Notes") }
            }
        }
    }
}