package com.muffar.remindtask.domain.model

import androidx.compose.ui.graphics.Color
import com.muffar.remindtask.ui.theme.color.MainColor

enum class PriorityType {
    HIGH, MEDIUM, LOW;

    companion object {
        fun PriorityType.toColor(): Color {
            return when (this) {
                HIGH -> MainColor.Red.kindaLight
                MEDIUM -> MainColor.Yellow.kindaLight
                LOW -> MainColor.Green.kindaLight
            }
        }
    }
}