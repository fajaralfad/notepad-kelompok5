package com.example.notepad_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface noteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: note)

    @Delete
    suspend fun deleteNote(note: note)

    @Query("SELECT * FROM Note ORDER BY title ASC")
    fun getOrderByTitle(): Flow<List<note>>

    @Query("SELECT * FROM Note ORDER BY dateAdded")
    fun getOrderByDateAddedBy(): Flow<List<note>>

    @Query("SELECT * FROM Note WHERE title LIKE '%' || :query || '%' OR disp LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchNotes(query: String): Flow<List<note>>

}
