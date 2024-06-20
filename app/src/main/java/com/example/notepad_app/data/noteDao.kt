package com.example.notepad_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY title ASC")
    fun getOrderByTitle(): Flow<List<Note>>

    @Query("SELECT * FROM Note ORDER BY dateAdded")
    fun getOrderByDateAddedBy(): Flow<List<Note>>
}
