package com.example.notepad_app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val dao: noteDao
}
