package com.example.mynoteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import java.data.model.NoteEntity
import java.data.local.NoteDao

@Database(entities = [NoteEntity::class], version = 3, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}