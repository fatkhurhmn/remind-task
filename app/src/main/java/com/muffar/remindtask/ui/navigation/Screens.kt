package com.muffar.remindtask.ui.navigation

sealed class Screens(val route: String) {
    data object Main : Screens("main")
    data object Tasks : Screens("main/tasks")
    data object Notes : Screens("main/notes")
    data object AddTask : Screens("task/add") {
        const val TASK = "task"
    }
}