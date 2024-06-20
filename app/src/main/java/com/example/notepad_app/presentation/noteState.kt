package com.example.notepad_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notepad_app.data.Note

class noteState {
    var notes: List<Note> = emptyList()
    val title: MutableState<String> = mutableStateOf(value = " ")
    val disp: MutableState<String> = mutableStateOf(value = " ")
}