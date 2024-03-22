package com.muffar.remindtask.data.local.db

import androidx.room.TypeConverter
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.StatusType.Companion.toStatusType
import com.muffar.remindtask.domain.model.StatusType.Companion.toValue
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

    @TypeConverter
    fun fromStatusType(statusType: StatusType): String = statusType.toValue()

    @TypeConverter
    fun toStatusType(statusType: String): StatusType = statusType.toStatusType()
}