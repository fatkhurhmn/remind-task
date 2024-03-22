package com.muffar.remindtask.data.local.db

import androidx.room.TypeConverter
import com.muffar.remindtask.domain.model.PriorityType
import java.util.UUID

class RoomTypeConverters {

    @TypeConverter
    fun fromPriorityType(priorityType: PriorityType): String = priorityType.name

    @TypeConverter
    fun toPriorityType(priorityType: String): PriorityType = PriorityType.valueOf(priorityType)

    @TypeConverter
    fun fromUUID(id: UUID?): String? = id?.toString()

    @TypeConverter
    fun toUUID(id: String?): UUID? = id?.let { UUID.fromString(it) }
}