package com.muffar.remindtask

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muffar.remindtask.main.MainScreen
import com.muffar.remindtask.navigation.Screens
import com.muffar.remindtask.notes.addNoteScreen
import com.muffar.remindtask.tasks.addTaskScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(Screens.Main.route) { MainScreen(navController = navController) }
        addTaskScreen(
            navController = navController,
            onNavigationBack = { navController.navigateUp() }
        )
        addNoteScreen(
            navController = navController,
            onNavigationBack = { navController.navigateUp() }
        )
    }
}