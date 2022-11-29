package com.example.mynoteapp.data.repository

import java.presentation.core.BaseRepository
import java.presentation.core.Resource
import java.data.local.NoteDao
import java.data.mapper.toNote
import java.data.mapper.toNoteEntity
import java.domain.model.Note
import java.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository, BaseRepository() {

    override fun addNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.addNote(java.data.mapper.toNoteEntity())
    }

    override fun deleteNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.deleteNote(java.data.mapper.toNoteEntity())
    }

    override fun editNote(note: Note): Flow<Resource<Note>> = flow {
        Resource.Loading(null)
        try {
            val data = noteDao.editNote(java.data.mapper.toNoteEntity())
            Resource.Success(data)
        } catch (ioException: IOException) {
            Resource.Error(ioException.localizedMessage ?: "Unknown error")
        }
    }

    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow {
        Resource.Loading(data = null)
        try {
            val data = noteDao.gAllNotes().map { java.data.mapper.toNote() }
            Resource.Success(data)
        } catch (ioException: IOException) {
            Resource.Error(ioException.localizedMessage ?: "Unknown error")
        }
    }
}