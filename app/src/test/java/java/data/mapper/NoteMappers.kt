package java.data.mapper

import java.data.model.NoteEntity
import java.domain.model.Note

fun Note.toNoteEntity() = NoteEntity(
    id = id,
    title = title,
    description = description,
    createdAt = createdAt
)
fun NoteEntity.toNote() = Note(
    id = id,
    title = title,
    description = description,
    createdAt = createdAt
)