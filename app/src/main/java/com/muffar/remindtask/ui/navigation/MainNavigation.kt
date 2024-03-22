package com.muffar.remindtask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muffar.remindtask.screen.main.MainScreen

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
    }
}