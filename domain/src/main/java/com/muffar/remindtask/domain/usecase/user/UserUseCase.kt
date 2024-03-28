package com.muffar.remindtask.domain.usecase.user

data class UserUseCase(
    val getHeaderType: GetHeaderType,
    val saveHeaderType: SaveHeaderType,
    val getNotesType: GetNotesType,
    val saveNotesType: SaveNotesType
)
