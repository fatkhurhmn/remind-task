package com.muffar.remindtask.ui.navigation

sealed class Screens(val route: String) {
    data object Tasks : Screens("main/tasks")
    data object Notes : Screens("main/notes")
}