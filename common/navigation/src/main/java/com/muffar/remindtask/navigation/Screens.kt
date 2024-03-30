package com.muffar.remindtask.navigation

sealed class Screens(val route: String) {
    data object Main : Screens("main")
    data object Tasks : Screens("main/tasks")
    data object AddTask : Screens("task/add") {
        const val TASK = "task"
    }
    data object Notes : Screens("main/notes")
    data object AddNote : Screens("note/add") {
        const val NOTE = "note"
    }
}