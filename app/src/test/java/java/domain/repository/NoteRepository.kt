package java.domain.repository

import java.presentation.core.Resource
import java.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {

    fun addNote(note: Note): Flow<Resource<Unit>>

    fun deleteNote(note: Note): Flow<Resource<Unit>>

    fun editNote(note: Note): Flow<Resource<Note>>

    fun getAllNotes(): Flow<Resource<List<Note>>>
}