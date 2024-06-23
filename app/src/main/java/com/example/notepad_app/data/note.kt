package com.example.notepad_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val disp: String,
    var dateAdded: Long
)
