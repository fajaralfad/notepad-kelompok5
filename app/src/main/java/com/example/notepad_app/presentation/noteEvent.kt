package com.example.notepad_app.presentation

import com.example.notepad_app.data.note

sealed interface noteEvent {
    object sortNotes : noteEvent
    data class deleteNote(var note: note) : noteEvent
    data class saveNote(
        var title: String,
        var disp: String
    ) : noteEvent
}
