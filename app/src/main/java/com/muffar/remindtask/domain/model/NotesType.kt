package com.muffar.remindtask.domain.model

enum class NotesType {
    LIST,
    GRID;

    companion object {
        fun NotesType.toValue(): String {
            return when (this) {
                LIST -> "List"
                GRID -> "Grid"
            }
        }

        fun String.toNotesType(): NotesType {
            return when (this) {
                "List" -> LIST
                "Grid" -> GRID
                else -> LIST
            }
        }
    }
}