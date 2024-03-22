package com.muffar.remindtask.domain.model

enum class StatusType {
    COMPLETED,
    UNCOMPLETED;

    companion object {
        fun StatusType.toValue(): String {
            return when (this) {
                COMPLETED -> "Completed"
                UNCOMPLETED -> "Uncompleted"
            }
        }

        fun String.toStatusType(): StatusType {
            return when (this) {
                "Completed" -> COMPLETED
                "Uncompleted" -> UNCOMPLETED
                else -> COMPLETED
            }
        }

        fun getAllStatusType(): List<StatusType> {
            return listOf(COMPLETED, UNCOMPLETED)
        }
    }
}