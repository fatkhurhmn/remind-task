package com.muffar.remindtask.data.repository.mapper

import com.muffar.remindtask.data.local.db.entity.NoteEntity
import com.muffar.remindtask.domain.model.Note

object NoteMapper {

    fun NoteEntity.toDomain(): Note {
        return Note(
            id = id,
            title = title,
            description = description,
            createdAt = createdAt
        )
    }

    fun Note.toEntity(): NoteEntity {
        return if (this.id == null) {
            NoteEntity(
                title = title,
                description = description,
                createdAt = createdAt
            )
        } else {
            NoteEntity(
                id = this.id,
                title = title,
                description = description,
                createdAt = createdAt
            )
        }
    }

    fun List<NoteEntity>.toDomain(): List<Note> {
        return map { it.toDomain() }
    }

    fun List<Note>.toEntity(): List<NoteEntity> {
        return map { it.toEntity() }
    }
}