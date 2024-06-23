package com.example.notepad_app.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notepad_app.data.note

class noteState(
    val notes: List<note> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val disp: MutableState<String> = mutableStateOf("")
) {
    fun copy(notes: List<note>) {

    }
}
